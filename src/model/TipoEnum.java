package model;

import java.util.HashMap;
import java.util.Map;

public enum TipoEnum {
	PESSOAL("Pessoal", 1),
	PROFISSIONAL("Profissional", 2);
	
	private String descricao;
	private int codigo;
	
	private TipoEnum(String descricao, int codigo) {
		this.descricao = descricao;
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public int getCodigo() {
		return codigo;
	}	
	
	private static final Map<Integer, TipoEnum> intToTypeMap = new HashMap<Integer, TipoEnum>();
	static {
	    for (TipoEnum type : TipoEnum.values()) {
	        intToTypeMap.put(type.codigo, type);
	    }
	}

	public static TipoEnum fromInt(int i) {
	    TipoEnum type = intToTypeMap.get(Integer.valueOf(i));
	    return type;
	}
	
}
