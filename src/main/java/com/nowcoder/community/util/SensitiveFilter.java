package com.nowcoder.community.util;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SensitiveFilter {

    private static final Logger logger = LoggerFactory.getLogger(SensitiveFilter.class);

    private static final String REPLACEMENT = "***";

    private Trie trie =  new Trie();
    @PostConstruct
    public void init() {
        try (
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("sensitive-words.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        ) {
            String keyword;
            while((keyword = reader.readLine()) != null) {
                this.addKeyword(keyword);
            }
        } catch(IOException e) {
            logger.error("加载敏感词文件失败：" + e.getMessage());
        }
    }

    private class TrieNode{
        private boolean isEnd = false;
        private Map<Character, TrieNode> subNodes;
        public TrieNode() {
            //son = new TrieNode[26];
            subNodes = new HashMap<>();
            //list = new ArrayList<>();
        }

        private boolean isKeywordEnd() {
            return isEnd;
        }
    }

    private class Trie {
        TrieNode root;
        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                if(node.subNodes.get(word.charAt(i)) == null) {
                    node.subNodes.put(word.charAt(i), new TrieNode());
                }
                node = node.subNodes.get(word.charAt(i));
            }
            node.isEnd = true;
        }
        public boolean search(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                if(node.subNodes.get(word.charAt(i)) == null) {
                    return false;
                }
                node = node.subNodes.get(word.charAt(i));
            }
            return node.isKeywordEnd();
        }
    }

    private void addKeyword(String keyword) {
        trie.insert(keyword);
    }

    public String filter(String text) {
        if(StringUtils.isBlank(text)) {
            return null;
        }

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            int p = -1;
            int t = i;
            for (int j = i+1; j < text.length(); j++) {
                if(trie.search(text.substring(t,j))) {
                    res.append(REPLACEMENT);
                    i = j-1;
                    p = j;
                }
            }
            if(p == -1) {
                res.append(text.charAt(i));
            }
        }
        return res.toString();
    }

    private boolean isSymbol(Character c) {
        // 0x2E80-0x9FFF 是东亚文字范围
        return !CharUtils.isAsciiAlphanumeric(c) && (c < 0x2E80 || c > 0x9FFF);
    }
}
