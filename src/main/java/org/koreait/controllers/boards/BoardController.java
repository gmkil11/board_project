package org.koreait.controllers.boards;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.koreait.commons.MemberUtil;
import org.koreait.commons.ScriptExceptionProcess;
import org.koreait.commons.Utils;
import org.koreait.entities.BoardData;
import org.koreait.models.board.BoardInfoService;
import org.koreait.models.board.BoardSaveService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController implements ScriptExceptionProcess {
    private final Utils utils;
    private final MemberUtil memberUtil;
    private final BoardSaveService saveService;
    private final BoardInfoService infoService;


    @GetMapping("/write/{bId}")
    public String write(@PathVariable String bId, @ModelAttribute BoardForm form, Model model) {
        commonProcess(bId, "write", model);

        return utils.tpl("board/write");
    }

    @GetMapping("/update/{seq}")
    public String update(@PathVariable Long seq, Model model) {
        return utils.tpl("board/update");
    }

    @PostMapping("/save")
    public String save(@Valid BoardForm form, Errors errors, Model model) {
        String mode = Objects.requireNonNullElse(form.getMode(), "write");
        String bId = form.getBId();

        commonProcess(bId, mode, model);

        if (errors.hasErrors()) {
            return utils.tpl("board/" + mode);
        }

        saveService.save(form);

        return "redirect:/board/list/" + bId;
    }

    @GetMapping("/view/{seq}")
    public String view(@PathVariable Long seq, Model model) {

        BoardData data = infoService.get(seq); // 조회 할 때 게시물이 없을 경우 예외를 발생시켜 주는 클래스
        model.addAttribute("boardData", data);
        return utils.tpl("board/view");
    }

    @GetMapping("/delete/{seq}")
    public String delete(@PathVariable Long seq) {
        infoService.delete(seq);

        return "redirect:/board/list";
    }

    private void commonProcess(String bId, String mode, Model model) {
        String pageTitle = "게시글 목록";
        if (mode.equals("write")) pageTitle = "게시글 작성";
        else if (mode.equals("update")) pageTitle = "게시글 수정";
        else if (mode.equals("view")) pageTitle = "게시글 제목";

        List<String> addCommonScript = new ArrayList<>();
        List<String> addScript = new ArrayList<>();

        if (mode.equals("write") || mode.equals("update")) {
            addCommonScript.add("ckeditor/ckeditor");
            addCommonScript.add("fileManager");

            addScript.add("board/form");
        }

        model.addAttribute("addCommonScript", addCommonScript);
        model.addAttribute("addScript", addScript);
        model.addAttribute("pageTitle", pageTitle);
    }
}
