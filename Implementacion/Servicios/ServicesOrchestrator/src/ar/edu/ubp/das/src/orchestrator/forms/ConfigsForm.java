package ar.edu.ubp.das.src.orchestrator.forms;

import java.util.LinkedList;
import java.util.List;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

public class ConfigsForm extends DynaActionForm {
	private List<String> regex;
	private List<String> words;
	private List<String> cssClasses;
	public List<String> getRegex() {
		return regex;
	}
	public void setRegex(List<String> regex) {
		this.regex = regex;
	}
	public List<String> getWords() {
		return words;
	}
	public void setWords(List<String> words) {
		this.words = words;
	}
	public List<String> getCssClasses() {
		return cssClasses;
	}
	public void setCssClasses(List<String> cssClasses) {
		this.cssClasses = cssClasses;
	}
	
	
}
