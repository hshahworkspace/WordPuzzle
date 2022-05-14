package com.amplinno.wordpuzzle.Test;

import com.amplinno.wordpuzzle.Util.WordUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class WordTestPuzzleUnitTests {

    private File resourceFile;

    @Before
    public void init() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        resourceFile = new File(classLoader.getResource("static/words.txt").getFile());
    }

    @After
    public void closure() {

    }

    @Test
    public void testWordTextFileExists() {
        assertTrue(resourceFile.exists());
    }

    @Test
    public void testFindRandomWordFromFile() {
        Assert.assertTrue(WordUtil.getRandomWord(resourceFile).length() > 0);
    }

    @Test
    public void testRandomWordMask() {
        Assert.assertTrue(WordUtil.maskWord(WordUtil.getRandomWord(resourceFile)).indexOf("*") >= 0);
    }

    @Test
    public void testFindWordInWordFile() {
        Assert.assertTrue(WordUtil.findWordInFile(resourceFile, "water"));
    }
}
