package com.demo.persistence;

import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.config.RootConfig;
import com.demo.config.ServletConfig;
import com.demo.domain.Review;
import com.demo.mapper.ReviewMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@ContextConfiguration(classes= {RootConfig.class, ServletConfig.class})
@Log4j
public class ReviewMapperTest {

	private Long[] bnoArray = { 46L, 45L, 44L, 43L, 42L ,41L ,40L , 39L ,38L, 37L};

	@Setter(onMethod_ = @Autowired)
	private ReviewMapper reviewMapper;

	@Test
	public void testReviewMapper() {
		log.info(reviewMapper);
	}

	/*
	 * @Test public void testCreate() { IntStream.rangeClosed(1, 10).forEach(i -> {
	 * ReviewVO reviewVO = ReviewVO.builder() .bno(bnoArray[i % 10])
	 * .mention("mention " + i) .rating(i % 10).build();
	 * 
	 * reviewMapper.insert(reviewVO); }); }
	 */

	@Test
	public void testReadOne() {
		Review vo = reviewMapper.findByBookId(2L);
		log.info(vo);
	}

	/*
	 * @Test public void testDelete() { int count = reviewMapper.delete(10L);
	 * log.info(count); }
	 */

	
	/*
	 * @Test public void testUpdate() { Long targetRno = 2L;
	 * 
	 * ReviewVO reviewVO = reviewMapper.readOne(targetRno);
	 * 
	 * reviewVO.setMention("hi there"); reviewVO.setRating(9);
	 * 
	 * int count = reviewMapper.update(reviewVO);
	 * 
	 * log.info("this is what you're looking for :" + count); }
	 */
	 
}
