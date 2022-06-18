package com.kodlamaio.rentACar.core.utilities.adapters.concretes;


import java.util.HashMap;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.core.utilities.adapters.abstracts.FindexService;
import com.kodlamaio.rentACar.entities.concretes.User;
@Service
public class FindexServiceAdapter implements FindexService {
	Random random = new Random();
	HashMap<String, Integer> findexScores;

	@Override
	public Integer findexScore(String nationalityId) {
		findexScores = new HashMap<String, Integer>();
		Integer findexScore = random.nextInt(1900);
		
		findexScores.put(nationalityId, findexScore);
		System.out.println(findexScore);
		return findexScore;
		
	}

}