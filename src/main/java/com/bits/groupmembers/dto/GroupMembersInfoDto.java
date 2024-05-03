package com.bits.groupmembers.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

//@Data
@NoArgsConstructor
@AllArgsConstructor
//@Getter
//@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupMembersInfoDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<StudentInfoDto> students;
	public List<StudentInfoDto> getStudents() {
		return students;
	}
	public void setStudents(List<StudentInfoDto> students) {
		this.students = students;
	}
}
