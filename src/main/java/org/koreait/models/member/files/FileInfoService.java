package org.koreait.models.member.files;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.koreait.entities.FileInfo;
import org.koreait.repositories.FileInfoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileInfoService {

    @Value("${file.upload.path}")
    private String uploadPath;
    private final FileInfoRepository repository;

    /**
     * 파일 등록 번호로 개별 조회
     * @param id
     * @return
     */
    public FileInfo get(Long id) {
        return null;
    }

    public List<FileInfo> getList(Options opts) {
        return null;
    }

    @Data @Builder
    static class Options {
        private String gid;
        private String location;
        private SearchMode mode = SearchMode.ALL;

    }

    static enum SearchMode {
        ALL,
        DONE,
        UNDONE
    }

}
