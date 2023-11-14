package org.koreait.controllers.members;

import lombok.extern.slf4j.Slf4j;
import org.koreait.commons.MemberUtils;
import org.koreait.commons.Utils;
import lombok.RequiredArgsConstructor;
import org.koreait.commons.Utils;
import org.koreait.entities.Member;
import org.koreait.models.member.MemberInfo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberUtils memberUtils;

    private final Utils utils;

    @GetMapping("/join")
    public String join() {

        return utils.tpl("member/join");
    }

    @GetMapping("/login")
    public String login(String redirectURL, Model model) {
        model.addAttribute("redirectURL", redirectURL);
        return utils.tpl("member/login");
    }

    @GetMapping("/info")
    @ResponseBody
    public void info() {
        Member member = memberUtils.getMember();
        // 로그인이 되어있을 경우 로그를 출력
        if(memberUtils.isLogin()) {
            log.info(member.toString());
        }
        log.info("로그인 여부 : {}", memberUtils.isLogin());
    }
/*
    public void info() {
        MemberInfo member = (MemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // 회원 정보를 갖고있는 객체

        log.info(member.toString());
    }
*/

/*
    public void info(@AuthenticationPrincipal MemberInfo memberInfo) {
        log.info(memberInfo.toString());
    }
*/

/*
    public void info(Principal principal) {
        String email = principal.getName();
        log.info(email);
    }
*/
}