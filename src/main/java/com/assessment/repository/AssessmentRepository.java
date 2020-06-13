package com.assessment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.dataobjects.FileInfo;

/**
 * @author Prerna Garg
 *
 * This is the repository interface responsible to handle the database operations.
 */
public interface AssessmentRepository extends JpaRepository<FileInfo, String> {

	/**
	 * This method finds the file details by using the fileName.
	 * 
	 * @param name
	 * @return Optional<FileInfo>
	 */
	@Query("SELECT f FROM FileInfo f WHERE f.imageName = :name")
	Optional<FileInfo> findByName(@Param("name") String name);
}
