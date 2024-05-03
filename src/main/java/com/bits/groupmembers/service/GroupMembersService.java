package com.bits.groupmembers.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bits.groupmembers.dto.GroupMembersInfoDto;
import com.bits.groupmembers.dto.StudentInfoDto;
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

//		File file = new File(classLoader.getResource("com/bits/groupmembers/service/Students.json").getFile());
		InputStream inputStream = getClass().getResourceAsStream("/com/bits/groupmembers/service/Students.json");

		try {
//			log.info("Reading student information from JSON file");
			System.out.println("Reading student information from JSON file");
			groupMembersInfoDto = mapper.readValue(inputStream, GroupMembersInfoDto.class);

		} catch (IOException e) {
			System.err.println("Error occured while reading student information " + e);
//			log.error("Error occured while reading student information", e);
		}
		return groupMembersInfoDto;
	}

	public List<StudentInfoDto> getAllGroupMembersForGivenElectives(String electives) {
//		ObjectMapper mapper = new ObjectMapper();
		ClassLoader classLoader = getClass().getClassLoader();

//		File file = new File(classLoader.getResource("com/bits/groupmembers/service/Students.json").getFile());
		InputStream inputStream = getClass().getResourceAsStream("/com/bits/groupmembers/service/Students.json");
		List<StudentInfoDto> studentInfoForElectives = getAllGroupMembersFrom(electives, inputStream);
		return studentInfoForElectives;
	}

	public List<StudentInfoDto> getAllGroupMembersFrom(String electives, InputStream inputStream) {
		List<StudentInfoDto> studentInfoForElectives = new ArrayList<>();

		try {
			System.out.println("Reading student information from JSON file");
//			log.info("Reading student information from JSON file");
			GroupMembersInfoDto groupMembersInfoDto = mapper.readValue(inputStream, GroupMembersInfoDto.class);
			List<StudentInfoDto> studentInfoDtoList = groupMembersInfoDto.getStudents();

			System.out.println("Retrieve student info for given electives " + electives);
//			log.info("Retrieve student info for given electives {}", electives);
			studentInfoForElectives = studentInfoDtoList.stream()
					.filter(a -> a.getElectiveCourses().contains(electives)).collect(Collectors.toList());
		} catch (IOException e) {
			// log.error("Error occured while reading student information", e);
			System.err.println("Error occured while reading student information " + e);
		}
		return studentInfoForElectives;
	}

}
