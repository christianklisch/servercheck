package de.christian_klisch.software.servercontrol.web;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import de.christian_klisch.software.servercontrol.controller.Application;

/**
 * Webcontroller to show tasks state and execute tasks selected in web
 * interface.
 * 
 * @author Christian Klisch
 * 
 *         License:
 * 
 *         This program is free software; you can redistribute it and/or modify
 *         it under the terms of the GNU General Public License version 2 as
 *         published by the Free Software Foundation.
 * 
 *         This program is distributed in the hope that it will be useful, but
 *         WITHOUT ANY WARRANTY; without even the implied warranty of
 *         MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *         General Public License for more details.
 * 
 *         You should have received a copy of the GNU General Public License
 *         along with this program; if not, write to the Free Software
 *         Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */
@Controller
public class WebAction {

    @Inject
    private Application application;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(Model model) {
	// System.out.println(application.toString());
	// System.out.println(application.getRequestMap().size());
	model.addAttribute("views", application.getAllViews());
	model.addAttribute("template", application.getFilledTemplate());

	return new ModelAndView("list");
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String l1(@RequestParam(value = "process", required = false) String process) {
	System.out.println("redirect " + process);

	application.executeFromWeb(process);

	return "redirect:/list";
    }
}
