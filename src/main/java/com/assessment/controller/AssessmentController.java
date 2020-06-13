package com.assessment.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.DataFormatException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.assessment.service.AssessmentService;

/**
 * @author Prerna Garg
 *
 * This is the controller class that handles the uri mappings.
 */
@RestController
@RequestMapping("Assessment")
public class AssessmentController {

	/**
	 * Injecting AssessmentService to call service methods.
	 */
	@Autowired
	private AssessmentService assessmentService;

	/**
	 * This method takes a file and then calls the service method to save it if it
	 * is in the required format else throws an exception.
	 * 
	 * @param file
	 * @return ResponseEntity<String>
	 * @throws DataFormatException
	 * @throws IOException
	 */
	@PostMapping("/saveImage")
	public ResponseEntity<String> saveImage(@RequestParam("file") MultipartFile file)
			throws DataFormatException, IOException {
		if (file.getContentType().trim().toLowerCase().startsWith("image")) {
			assessmentService.save(file);
			return new ResponseEntity<String>("Image Saved Successfully.", HttpStatus.OK);
		}
		throw new DataFormatException();
	}

	/**
	 * This method takes an imageName as a string parameter and then returns the
	 * corresponding image.
	 * 
	 * @param imageName
	 * @return ResponseEntity<byte[]>
	 * @throws FileNotFoundException
	 */
	@GetMapping(value = "/get/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> getImage(@PathVariable("imageName") String imageName) throws FileNotFoundException {
		return new ResponseEntity<>(assessmentService.getImage(imageName), HttpStatus.OK);
	}

	/**
	 * This method takes an input parameter of type Integer and converts it to the
	 * binary format.
	 * 
	 * @param number
	 * @return String
	 */
	@GetMapping("/convert/{number}")
	public String getImage(@PathVariable("number") int number) {
		return assessmentService.convertIntToBinary(number);
	}
}
