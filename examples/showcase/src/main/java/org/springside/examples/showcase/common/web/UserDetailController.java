package org.springside.examples.showcase.common.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.examples.showcase.common.entity.User;
import org.springside.examples.showcase.common.service.AccountManager;

/**
 * 使用@ModelAttribute, 实现Struts2 Preparable二次绑定的效果。 
 * 因为@ModelAttribute被默认执行, 而其他的action url中并没有${id}，所以需要独立出一个Controller.
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/common/user/")
public class UserDetailController {

	private AccountManager accountManager;

	@RequestMapping(value = "update/{id}")
	public String updateForm(Model model) {
		return "common/userForm";
	}

	@RequestMapping(value = "save/{id}")
	public String save(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
		accountManager.saveUser(user);
		redirectAttributes.addFlashAttribute("message", "保存用户成功");
		return "redirect:/common/user/";
	}

	@ModelAttribute("user")
	public User getAccount(@PathVariable("id") Long id) {
		return accountManager.getUser(id);
	}

	@Autowired
	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}
}
