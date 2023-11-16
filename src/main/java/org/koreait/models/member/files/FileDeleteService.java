package org.koreait.models.member.files;

import lombok.RequiredArgsConstructor;
import org.koreait.commons.MemberUtils;
import org.koreait.commons.exceptions.AuthorizationException;
import org.koreait.entities.FileInfo;
import org.koreait.entities.Member;
import org.koreait.repositories.FileInfoRepository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class FileDeleteService {
    private final MemberUtils memberUtils;
    private final FileInfoService infoService;
    private final FileInfoRepository repository;

    public void delete(Long id) {
        FileInfo item = infoService.get(id);

        // 파일 삭제 권한 체크 시작
        String createdBy = item.getCreatedBy(); // 파일 업로드한 사용자 이메일를 가져옴
        Member member = memberUtils.getMember();
        if (createdBy != null && !createdBy.isBlank() && !memberUtils.isAdmin() && (!memberUtils.isLogin() || (memberUtils.isLogin() && member.getEmail().equals(createdBy)))) {

            throw new AuthorizationException("UnAuthorized.delete.Files");
        }
        // 파일 삭제 권한 끝

        /**
         * 1. 파일 삭제,
         * 2. thumbs 삭제
         * 3. 파일 정보 삭제
         */

        File file = new File(item.getFilePath());
        if (file.exists()) file.delete();

        String[] thumbsPath = item.getThumbPath();
        if (thumbsPath != null && thumbsPath.length > 0) {
            Arrays.stream(thumbsPath).forEach(p -> {
                File thumb = new File(p);
                if (thumb.exists()) thumb.delete();
            });
        }

        repository.delete(item);
        repository.flush();

     }
}
