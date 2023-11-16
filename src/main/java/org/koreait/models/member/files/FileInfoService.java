package org.koreait.models.member.files;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.koreait.configs.FileUploadConfig;
import org.koreait.entities.FileInfo;
import org.koreait.repositories.FileInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileInfoService {

    @Autowired
    private FileUploadConfig fileUploadConfig;

    private String fileUploadPath = fileUploadConfig.getPath();
    private String fileUploadUrl = fileUploadConfig.getUrl();

    private final HttpServletRequest request;

    private final FileInfoRepository repository;

    /**
     * 파일 등록 번호로 개별 조회
     * @param id
     * @return
     */
    public FileInfo get(Long id) {

        FileInfo item = repository.findById(id).orElseThrow(FileNotFoundException::new);

        addFileInfo(item);

        return item;
    }

    public List<FileInfo> getList(Options opts) {
        List<FileInfo> items = repository.getFiles(opts.getGid(), opts.getLocation(), opts.getMode().name());
        items.forEach(this::addFileInfo);
        return items;
    }

    public List<FileInfo> getListAll(String gid, String location){
        Options opts= Options.builder()
                .gid(gid)
                .location(location)
                .mode(SearchMode.ALL)
                .build();
        return getList(opts);
    }

    public List<FileInfo> getListAll(String gid){
        return getListAll(gid, null);
    }

    public List<FileInfo> getListDone(String gid, String location) {
        Options opts= Options.builder()
                .gid(gid)
                .location(location)
                .mode(SearchMode.DONE)
                .build();
        return getList(opts);
    }

    public List<FileInfo> getListDone(String gid) {
        return getListDone(gid, null);
    }

    /**
     * 파일 업로드 서버 경로 (filePath)
     * 파일 서버 접속 URL (fileUrl)
     * 썸네일 경로 (thumbPath), 썸네일(thumbsUrl)
     * @param item
     */
    public void addFileInfo(FileInfo item) {
        long id = item.getId();
        String extension = item.getExtension();
        String fileName = extension == null || extension.isBlank() ? "" + id : id + "." + extension;
        long folder = id % 10L;

        // 파일 업로드 서버 경로
        String filePath = fileUploadPath + folder + "/" + fileName;

        // 파일 서버 접속 URL
        String fileUrl = request.getContextPath() + fileUploadUrl + folder + "/" + fileName;

        // 썸네일 경로(thumbsPath)
        String thumbPath = getUploadThumbPath() +  folder;
        File thumbDir = new File(getUploadThumbPath());
        if (!thumbDir.exists()) {
            thumbDir.mkdirs();
        }

            // 100_100_1.png 형식
        String[] thumbsPath = thumbDir.list((dir, name) -> name.indexOf("_" + fileName) != -1);

        // 썸네일 URL(thumbsUrl)
        String[] thumbsUrl = Arrays.stream(thumbsPath).map(s -> s.replace(fileUploadPath, request.getContextPath()+fileUploadUrl)).toArray(String[]::new);

        item.setFilePath(filePath);
        item.setFileUrl(fileUrl);
        item.setThumbPath(thumbsPath);
        item.setThumbUrl(thumbsUrl);
    }

    public String getUploadThumbPath() {
        // thumbs/folder/가로_세로_파일명
        return fileUploadPath + "thumbs/";
    }

    private String getUploadThumbUrl() {
        return fileUploadUrl + "thumbs/";
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

