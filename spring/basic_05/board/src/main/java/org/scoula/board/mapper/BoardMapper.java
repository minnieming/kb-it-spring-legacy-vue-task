package org.scoula.board.mapper;

import org.scoula.board.domain.BoardVO;

import java.util.List;

public interface BoardMapper {

    // get
    public List<BoardVO> getList();
    public BoardVO get(Long no);

    // create
    public void create(BoardVO board);

    // update
    public int update(BoardVO board);

    // delete
    public int delete(Long no);
}
