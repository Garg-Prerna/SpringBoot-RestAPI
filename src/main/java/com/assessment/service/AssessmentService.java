package com.assessment.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.assessment.dataobjects.FileInfo;
import com.assessment.repository.AssessmentRepository;

/**
 * @author Prerna Garg
 * 
 * This is the service class that handles all the logic and
 *         acts as an intermediate layer between controller and repository.
 */
@Service
public class AssessmentService {

	/**
	 * Injecting AssessmentRepository so as to perform the database operations.
	 */
	@Autowired
	private AssessmentRepository assessmentRepository;

	/**
	 * Method that saves the file data to database.
	 * 
	 * @param file
	 * @throws IOException
	 */
	public void save(MultipartFile file) throws IOException {
		FileInfo fileInformation = new FileInfo(file.getOriginalFilename(), file.getBytes());
		if (!assessmentRepository.findByName(file.getOriginalFilename()).isPresent()) {
			assessmentRepository.saveAndFlush(fileInformation);
		} else {
			throw new DataIntegrityViolationException(
					"An image with the same name is already present in the database.");
		}

	}

	/**
	 * Method that fetches the image from the database based on the image name.
	 * 
	 * @param imageName
	 * @throws FileNotFoundException
	 */
	public byte[] getImage(String imageName) throws FileNotFoundException {
		if (assessmentRepository.findByName(imageName).isPresent()) {
			return assessmentRepository.findByName(imageName).get().getPicBytes();
		} else {
			throw new FileNotFoundException();
		}
	}

	/**
	 * Method that converts the Integer data to corresponding Binary string.
	 * 
	 * @param number
	 * @return String
	 */
	public String convertIntToBinary(int number) {
		StringBuilder binaryString = new StringBuilder("");
		while (number > 0) {
			binaryString = new StringBuilder(String.valueOf(number % 2)).append(binaryString);
			number /= 2;
		}
		return binaryString.toString();
	}

}
