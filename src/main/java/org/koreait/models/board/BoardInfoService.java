package org.koreait.models.board;

import lombok.RequiredArgsConstructor;
import org.koreait.entities.Board;
import org.koreait.entities.BoardData;
import org.koreait.repositories.BoardDataRepository;
import org.koreait.repositories.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardInfoService {
    private final BoardDataRepository boardDataRepository;

    public BoardData get(Long seq){
        BoardData data = boardDataRepository.findById(seq).orElseThrow(BoardDataNotFoundException::new);
        return data;
    }

    public void delete(Long seq) {

        Optional<BoardData> optionalBoardData = boardDataRepository.findById(seq);

        if (optionalBoardData.isPresent()) {
            BoardData boardData = optionalBoardData.get();
            boardDataRepository.delete(boardData);
        }
    }
}
