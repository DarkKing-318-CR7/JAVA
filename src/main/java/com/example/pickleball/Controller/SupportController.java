package com.example.pickleball.Controller;

import com.example.pickleball.model.dto.SupportDto;
import com.example.pickleball.Service.SupportService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SupportController {

    @Autowired
    private SupportService supportService;

    @GetMapping("/support")
    public String showSupportForm(Model model) {
        model.addAttribute("supportRequest", new SupportDto());
        return "support/support";
    }

    @PostMapping("/support")
    public String handleSupportForm(@Valid @ModelAttribute("supportRequest") SupportDto supportDto,
                                    BindingResult bindingResult,
                                    Model model) {
        if (bindingResult.hasErrors()) {
            return "support/support"; // Nếu có lỗi validation thì trả lại form
        }

        // 👉 Lưu dữ liệu support vào database
        supportService.saveSupportRequest(supportDto);

        // ✅ Thông báo thành công
        model.addAttribute("successMessage", "Cảm ơn bạn đã liên hệ. Chúng tôi sẽ phản hồi trong thời gian sớm nhất!");

        model.addAttribute("supportRequest", new SupportDto()); // Reset form
        return "support/support";
    }
}
