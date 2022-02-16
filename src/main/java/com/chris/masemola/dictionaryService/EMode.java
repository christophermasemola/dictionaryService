package com.chris.masemola.dictionaryService;

import java.util.HashMap;
import java.util.Map;

/**
 * This Enum is for translation mode description
 * author: CHRISTOPHER MAEMOLA
 * */
public enum EMode
{
	NO_QUOTES(1, "no quotes"),
	WITH_QUOTES(2, "with quotes");


	private final int code;
	private final String description;
	private static Map<Integer, EMode> values = new HashMap<>();

	static
	{
		for(EMode anEnum : EMode.values())
		{
			values.put(anEnum.getCode(), anEnum);
		}
	}

	EMode(int code, String description)
	{
		this.code = code;
		this.description = description;
	}

	/**
	 * Gets the EMode enum value of the code
	 * @param code int
	 * @return EMode enum value of the code
	 * */
	public static EMode of(int code)
	{
		return values.get(code);
	}

	/**
	 * Gets the translation mode code
	 * @return int translation code value
	 * */
	public int getCode()
	{
		return code;
	}

	/**
	 * Gets the translation mode description
	 * @return string translation description value
	 * */
	public String getDescription()
	{
		return description;
	}
}
