package com.bits.groupdetails.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bits.groupdetails.dto.GroupMembersInfoDto;
import com.bits.groupdetails.dto.StudentInfoDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GroupMembersService {

	@Autowired
	private ObjectMapper mapper;
	
	public GroupMembersInfoDto getGroupMembersDetails() {
		GroupMembersInfoDto groupMembersInfoDto = null;

		ObjectMapper mapper = new ObjectMapper();
		ClassLoader classLoader = getClass().getClassLoader();

		File file = new File(classLoader.getResource("com/bits/groupdetails/service/Students.json").getFile());
		try {
			groupMembersInfoDto = mapper.readValue(file, GroupMembersInfoDto.class);

		} catch (IOException e) {
			// Ignore
		}
		return groupMembersInfoDto;
	}

	public List<StudentInfoDto> getAllGroupMembersForGivenElectives(String electives) {
		ClassLoader classLoader = getClass().getClassLoader();

		File file = new File(classLoader.getResource("com/bits/groupdetails/service/Students.json").getFile());
		List<StudentInfoDto> studentInfoForElectives = getAllGroupMembersFrom(electives, file);
		return studentInfoForElectives;
	}
	
	public List<StudentInfoDto> getAllGroupMembersFrom(String electives, File sourceData) {
		List<StudentInfoDto> studentInfoForElectives = new ArrayList<>();

		try {
			GroupMembersInfoDto groupMembersInfoDto = mapper.readValue(sourceData, GroupMembersInfoDto.class);
			List<StudentInfoDto> studentInfoDtoList = groupMembersInfoDto.getStudents();
			
			studentInfoForElectives = studentInfoDtoList.stream().filter(a -> a.getElectiveCourses().contains(electives)).collect(Collectors.toList());
		} catch (IOException e) {
			// Ignore
		}
		return studentInfoForElectives;
	}

}
