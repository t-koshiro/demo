package com.example.demo.base.controller.impl;

import java.util.List;

import com.example.demo.base.controller.MemberInformationController;
import com.example.demo.base.dao.memberInformation.MemberInformationDto;
import com.example.demo.base.domain.memberInformation.MemberForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo.base.service.impl.MemberInformationServiceImpl;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberInformationControllerImpl implements MemberInformationController {

    @Autowired
    MemberInformationServiceImpl memberInformationServiceImpl;

    //会員情報のGET用メソッド
    @GetMapping("/memberInformation_contents")
    public String getMemberInformation(Model model) {

        model.addAttribute("contents", "base/memberInformation::memberInformation_contents");

        List<MemberInformationDto> memberInformationDtoList = memberInformationServiceImpl.selectMany();

        model.addAttribute("memberInformationDtoList", memberInformationDtoList);

        return "base/homeLayout";
    }

    //新規会員登録のGETメソッド
    @GetMapping("/newMemberRegistration_contents")
    public String getNewMemberRegistration(@ModelAttribute MemberForm form, Model model) {
        model.addAttribute("contents", "base/newMemberRegistration::newMemberRegistration_contents");
        return "base/homeLayout";
    }

    //新規会員登録のPOSTメソッド
    @PostMapping("/newMemberRegistration_contents")
    public String postNewMemberRegistration(@ModelAttribute MemberForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return getNewMemberRegistration(form, model);
        }
        System.out.println(form);

        MemberForm memberForm = new MemberForm();

        memberForm.setMemberId(form.getMemberId()); //会員ID
        memberForm.setMemberName(form.getMemberName()); //会員名
        memberForm.setBirthday(form.getBirthday()); //生年月日
        memberForm.setAge(form.getAge()); //年齢
        memberForm.setPhoneNumber(form.getPhoneNumber()); //電話番号
        memberForm.setAddress(form.getAddress()); //住所

        boolean result = memberInformationServiceImpl.insertOne(memberForm);

        //会員登録結果の判定
        if (result = true) {
            System.out.println("insert成功");
        } else {
            System.out.println("insert失敗");
        }

        return "base/login";
    }

    //会員詳細画面のGETメソッド
    @GetMapping("/memberDetail/{id:.+}")
    public String getMemberDetail(@ModelAttribute MemberForm memberForm, Model model, @PathVariable("id") String memberId) {

        System.out.println("memberId =" + memberId);

        model.addAttribute("contents", "base/memberDetail::memberDetail_contents");

        if (memberId != null && memberId.length() > 0) {

            MemberInformationDto memberInformationDto = memberInformationServiceImpl.selectOne(memberId);

            memberForm.setMemberId(memberInformationDto.getMemberId()); //会員ID
            memberForm.setMemberName(memberInformationDto.getMemberName()); //会員名
            memberForm.setBirthday(memberInformationDto.getBirthday()); //生年月日
            memberForm.setAge(memberInformationDto.getAge()); //年齢
            memberForm.setPhoneNumber(memberInformationDto.getPhoneNumber()); //電話番号
            memberForm.setAddress(memberInformationDto.getAddress()); //住所

            model.addAttribute("memberForm",memberForm);
        }
        return "base/homeLayout";
    }

    //更新用のpostメソッド
    @PostMapping(value = "/memberDetail", params = "update")
    public String postMemberDetailUpdate(@ModelAttribute MemberForm memberForm,Model model){
        System.out.println("更新ボタンの処理");

        MemberInformationDto memberInformationDto = new MemberInformationDto();

        memberInformationDto.setMemberName(memberForm.getMemberName()); //会員名
        memberInformationDto.setBirthday(memberForm.getBirthday()); //生年月日
        memberInformationDto.setAge(memberForm.getAge()); //年齢
        memberInformationDto.setPhoneNumber(memberForm.getPhoneNumber()); //電話番号
        memberInformationDto.setAddress(memberForm.getAddress()); //住所

        boolean result = memberInformationServiceImpl.updateOne(memberInformationDto);

        if(result){
            model.addAttribute("result","更新成功");
        } else {
          model.addAttribute("result","更新失敗");
        }
        return getMemberInformation(model);
    }

    @PostMapping(value = "memberDetail",params = "delete")
    public String postMemberDelete(@ModelAttribute MemberForm memberForm, Model model) {

        System.out.println("削除ボタンの処理");

        boolean result = memberInformationServiceImpl.deleteOne(memberForm.getMemberId());

        if(result){
            model.addAttribute("result","削除成功");
        } else {
            model.addAttribute("result","削除失敗");
        }
        return getMemberInformation(model);
    }
}
