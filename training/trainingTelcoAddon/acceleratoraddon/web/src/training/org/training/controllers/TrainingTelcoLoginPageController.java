/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package training.org.training.controllers;

import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.Breadcrumb;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.acceleratorstorefrontcommons.forms.GuestForm;
import de.hybris.platform.acceleratorstorefrontcommons.forms.LoginForm;
import de.hybris.platform.b2ctelcoaddon.controllers.pages.TelcoLoginPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import training.org.training.forms.TrainingTelcoRegisterForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collections;


@Controller
@RequestMapping(value = "/login")
public class TrainingTelcoLoginPageController extends TelcoLoginPageController {

	@RequestMapping(method = RequestMethod.GET)
	public String doLogin(@RequestHeader(value = "referer", required = false) final String referer,
						  @RequestParam(value = "error", defaultValue = "false") final boolean loginError, final Model model,
						  final HttpServletRequest request, final HttpServletResponse response, final HttpSession session)
			throws CMSItemNotFoundException
	{
		return super.doLogin(referer,loginError,model,request,response,session);
	}

	@Override
	protected String getDefaultLoginPage(final boolean loginError, final HttpSession session, final Model model)
			throws CMSItemNotFoundException {

		this.prepareForms(model, session);

		storeCmsPageInModel(model, getCmsPage());
		setUpMetaDataForContentPage(model, (ContentPageModel) getCmsPage());
		model.addAttribute("metaRobots", "index,nofollow");
		addRegistrationConsentDataToModel(model);
		final Breadcrumb loginBreadcrumbEntry = new Breadcrumb("#", getMessageSource().getMessage("header.link.login",
				null, "header.link.login", getI18nService().getCurrentLocale()),null);

		model.addAttribute("breadcrumbs", Collections.singletonList(loginBreadcrumbEntry));

		if (loginError)	{
			model.addAttribute("loginError", Boolean.valueOf(loginError));
			GlobalMessages.addErrorMessage(model, "login.error.account.not.found.title");
		}
		return getView();
	}

	private void prepareForms(final Model model, final HttpSession session){
		final String username = (String) session.getAttribute(SPRING_SECURITY_LAST_USERNAME);
		if (username != null){
			session.removeAttribute(SPRING_SECURITY_LAST_USERNAME);
		}

		final LoginForm loginForm = new LoginForm();
		loginForm.setJ_username(username);

		model.addAttribute(loginForm);
		model.addAttribute(new TrainingTelcoRegisterForm());
		model.addAttribute(new GuestForm());

	}
}
