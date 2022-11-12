package com.egs.bankservice.repository;


import com.egs.bankservice.common.repository.BaseRepository;
import com.egs.bankservice.entity.Card;


public interface CardRepository extends BaseRepository<Card> {

	public Card findByCardNumber(Long cardNumber);

}
