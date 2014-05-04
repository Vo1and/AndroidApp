package com.plumeus.jfftpersonality;

public class Question {
	private int ID;
	private String QUESTION;
	private String OPTA;
	private String OPTB;
	private String OPTC;
	private Integer OptaValue;
	private Integer OptbValue;
	private Integer OptcValue;
	private int[] OptValuesArray;

	public Question()
	{
		ID=0;
		QUESTION="";
		OPTA="";
		OPTB="";
		OPTC="";
		OptaValue=0;
		OptbValue=0;
		OptcValue=0;
		OptValuesArray = new int[3];
	}

	public Question(String qUESTION, String oPTA, Integer optaValue, String oPTB, Integer optbValue, String oPTC, Integer optcValue) {
		
		QUESTION = qUESTION;
		OPTA = oPTA;
		OptaValue = optaValue;
		OPTB = oPTB;
		OptbValue = optbValue;
		OPTC = oPTC;
		OptcValue = optcValue;
	}

	public int getID()
	{
		return ID;
	}

	public String getQUESTION() {
		return QUESTION;
	}

	public String getOPTA() {
		return OPTA;
	}

	public String getOPTB() {
		return OPTB;
	}

	public String getOPTC() {
		return OPTC;
	}

	public Integer getOptaValue() {
		return OptaValue;
	}

	public Integer getOptbValue() {
		return OptbValue;
	}

	public Integer getOptcValue() {
		return OptcValue;
	}

	public Integer getOptValue(Integer optKey) {
		return OptValuesArray[optKey];
	}


	public void setID(int id)
	{
		ID=id;
	}

	public void setQUESTION(String qUESTION) {
		QUESTION = qUESTION;
	}

	public void setOPTA(String oPTA) {
		OPTA = oPTA;
	}

	public void setOPTB(String oPTB) {
		OPTB = oPTB;
	}

	public void setOPTC(String oPTC) {
		OPTC = oPTC;
	}

	public void setOptaValue(Integer optaValue) {
		OptaValue = optaValue;
	}

	public void setOptbValue(Integer optbValue) {
		OptbValue = optbValue;
	}

	public void setOptcValue(Integer optcValue) {
		OptcValue = optcValue;
	}

	public void setOptValuesArray(Integer optKey, Integer optValue) {
		OptValuesArray[optKey] = optValue;
	}
	
}
