package com.ahana.api.domain.config;

import java.util.List;

import com.ahana.commons.system.domain.common.AhanaVO;

public class ConfigWrapper implements AhanaVO {

	private static final long serialVersionUID = 1L;
	
	private List<AhanaVO> ahanaVOs;

	public List<AhanaVO> getAhanaVOs() {
		return ahanaVOs;
	}

	public void setAhanaVOs(List<AhanaVO> ahanaVOs) {
		this.ahanaVOs = ahanaVOs;
	}

}