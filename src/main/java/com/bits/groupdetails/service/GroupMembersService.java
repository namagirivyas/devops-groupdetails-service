package com.bits.groupdetails.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bits.groupdetails.dto.GroupMembersInfoDto;
import com.bits.groupdetails.dto.StudentInfoDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import static java.util.Collections.EMPTY_LIST;

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
			// log.error("Error occured while getting group members details", e); 
		}
		return groupMembersInfoDto;
	}

	public List<StudentInfoDto> getAllGroupMembersForGivenElectives(String electives) {
		ClassLoader classLoader = getClass().getClassLoader();

		File file = new File(classLoader.getResource("com/bits/groupdetails/service/Students.json").getFile());
		return getAllGroupMembersFrom(electives, file);
	}
	
	public List<StudentInfoDto> getAllGroupMembersFrom(String electives, File sourceData) {

		try {
			GroupMembersInfoDto groupMembersInfoDto = mapper.readValue(sourceData, GroupMembersInfoDto.class);
			List<StudentInfoDto> studentInfoDtoList = groupMembersInfoDto.getStudents();
			
			return studentInfoDtoList.stream().filter(a -> a.getElectiveCourses().contains(electives)).collect(Collectors.toList());
		} catch (IOException e) {
			// Ignore
			// log.error("Error occured while getting group members details based on electives", e);
		}
		return new ArrayList<>();
	}

	public StudentInfoDto getStudentInfoFrom(String studentId, File sourceData) {
		StudentInfoDto studentInfoToReturn = null;

		try {
			GroupMembersInfoDto groupMembersInfoDto = mapper.readValue(sourceData, GroupMembersInfoDto.class);
			List<StudentInfoDto> studentInfoDtoList = groupMembersInfoDto.getStudents();
			
			Optional<StudentInfoDto> studentInfoOptional = studentInfoDtoList.stream().filter(a -> a.getStudentId().equalsIgnoreCase(studentId)).findFirst();
			if(studentInfoOptional.isPresent()) {
				studentInfoToReturn = studentInfoOptional.get();
			} else {
				// log.warn("Student {} not found", studentId );
                                 System.out.println("StudentId " + studentId + " not found");
			}
		} catch (IOException e) {
			System.err.println(e);
			// Ignore
			// log.error("Error occured while getting student information based on studentId", e);
		}
		return studentInfoToReturn;
	}

	public StudentInfoDto getStudentInfo(String studentId) {
		ClassLoader classLoader = getClass().getClassLoader();

		File file = new File(classLoader.getResource("com/bits/groupdetails/service/Students.json").getFile());
		return getStudentInfoFrom(studentId, file);
	}

}
