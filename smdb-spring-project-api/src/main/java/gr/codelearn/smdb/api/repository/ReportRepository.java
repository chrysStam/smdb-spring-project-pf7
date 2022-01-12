package gr.codelearn.smdb.api.repository;

import gr.codelearn.smdb.api.domain.Content;
import gr.codelearn.smdb.api.domain.Genre;
import gr.codelearn.smdb.api.domain.Role;
import gr.codelearn.smdb.api.transfer.ContentOfContributorByIdByGenreDto;
import gr.codelearn.smdb.api.transfer.NoOfContentPerGenreDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Content, Long> {

//	 Report 1: Return the top X high-rated content.
	@Query("select DISTINCT f from Content f ORDER BY f.imdbScore DESC")
	List<Content> findTopXHighIMDBScore(PageRequest pageable);

//	Report 2: Return all content associated with a given individual regardless of hir/her contributing role.
	// BY ID
	@Query("select c from Content c join c.contentContributors cC where cC.key.personId = ?1")
	List<Content>findContributionsOfPersonById(Long personId);

	//	BY FULL NAME
	@Query("select DISTINCT f from Content f JOIN f.contentContributors c JOIN c.person p where p.id =(select " +
			"DISTINCT p" +
			".id from Person p WHERE p.name = :name and p.surname=:surname)")
	List<Content> findByContributorByFullName(String name,String surname);


//	Report 3: Return all content associated with a given individual for a given contributing role.
	// BY ID
	@Query("select c from Content c join c.contentContributors cC where cC.key.personId = ?1 and cC.key.role = ?2")
	List<Content> findContributionsOfPersonByIdAndRole(Long personId, Role role);

	//	BY FULL NAME
	@Query("select DISTINCT f from Content f JOIN f.contentContributors c JOIN c.key k JOIN c.person p where p.id =" +
			"(select DISTINCT p.id from Person p WHERE p.name = :name and p.surname=:surname) AND (k.role=:role) ")
	List<Content> findByContributorByFullNameAndRole(String name, String surname, Role role);


//	Report 4: Return all content for a given genre
	List<Content> findAllByGenresContaining(Genre genre);


//	Report 5: Return the number of shows per genre
	@Query(value="SELECT genres, count(content_id) as number FROM CONTENT_GENRES group by genres" , nativeQuery = true)
	List<NoOfContentPerGenreDto> findNoOfContentPerGenre();


//	Report 6: Return the numbers of shows per year per genre
	@Query("select c.releaseYear from Content  c group by c.releaseYear")
	List<Integer> findYears();

	@Query(value="SELECT cg.genres, count(c.id) as number " +
			"FROM CONTENT_GENRES as cg inner join contents as c on cg.content_id = c.id " +
			"where release_year= ?1 group by genres", nativeQuery = true)
	List<NoOfContentPerGenreDto> findNoOfContentByReleaseYearPerGenre(Integer year);


//	Report 7: Return all content associated with a given individual organized per genre
	@Query(value="SELECT foo.*, cc.role, cc.person_id as personId "+
				   "FROM  ( SELECT c.id as contentId, c.imdb_score as imdbScore, c.language, c.motion_picture_rating as MPR, " +
							" c.plot_summary as plotSummary, c.release_year as releaseYear, c.title, cg.genres " +
							"FROM CONTENT_GENRES AS CG INNER JOIN CONTENTS AS C ON C.ID = CG.CONTENT_ID) AS FOO " +
							"INNER JOIN CONTENT_CONTRIBUTORS AS CC ON FOO.contentId = CC.CONTENT_ID " +
					"WHERE person_id = ?1 and genres = ?2", nativeQuery = true)
	List<ContentOfContributorByIdByGenreDto> findContentOfContributorByIdByGenre(Long id, String genre);

}
