package com.sptek.demo2;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface BoardRepository2 extends CrudRepository<Board, Long> {

}
