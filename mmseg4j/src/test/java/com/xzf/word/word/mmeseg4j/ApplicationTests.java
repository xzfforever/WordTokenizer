package com.xzf.word.word.mmeseg4j;

import com.chenlb.mmseg4j.ComplexSeg;
import com.chenlb.mmseg4j.Dictionary;
import com.chenlb.mmseg4j.MMSeg;
import com.chenlb.mmseg4j.Word;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Value("${mmseg.dic.path}")
	private String mmsegPath;

	public List<String> segmentWords(String txt) throws Exception{
		List<String> words = new ArrayList<String>();
		File cfgFile = ResourceUtils.getFile("classpath:data");
		Dictionary dic = Dictionary.getInstance(cfgFile);
		MMSeg mmSeg = new MMSeg(new StringReader(txt), new ComplexSeg(dic));
		Word word = null;
		try {
			while ((word = mmSeg.next()) != null) {
				String w = word.getString();
				words.add(w);
			}
		} catch (IOException e) {
			System.out.println("Exception:"+e);
		}
		return words;
	}

	@Test
	public void testMmseg4j() throws Exception{
		System.out.println(mmsegPath);
		String txt = "汽车打蜡";
		//txt = "京华时报1月23日报道 昨天，受一股来自中西伯利亚的强冷空气影响，本市出现大风降" +
			//	"温天气，白天最高气温只有零下7摄氏度，同时伴有6到7级的偏北风。";
		//txt = "研究生命起源";
		//txt = "手机电子书    abc   http://www.sjshu.com";
		//txt = "Apple 苹果 MacBook Pro MB991CH/A 13.3m寸宽屏笔记本(Ⅱ,⑩)";
		System.out.println(segmentWords(txt));
	}



}
