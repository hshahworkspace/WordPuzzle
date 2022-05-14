package com.amplinno.wordpuzzle.Form;

import lombok.Data;

@Data
public class WordResult {

    public String word;
    public Integer wordLength;
    public String result;
    public String message;
}
