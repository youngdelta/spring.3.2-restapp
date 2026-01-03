package devfun.bookstore.rest;

import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import devfun.bookstore.common.domain.Book;
import devfun.bookstore.rest.controller.BookController;
import devfun.bookstore.rest.domain.BookResource;

public class BookResourceAssembler extends RepresentationModelAssemblerSupport<Book, BookResource> {

	public BookResourceAssembler() {
		super(BookController.class, BookResource.class);
	}

	@Override
	public BookResource toModel(Book book) {
		BookResource resource = createModelWithId(book.getId(), book);
		resource.setBookId(book.getId());
		resource.setTitle(book.getTitle());
		resource.setCreator(book.getCreator());
		resource.setDate(book.getDate());
		resource.setType(book.getType());
		
		BeanUtils.copyProperties(book, resource);
		return resource;
	}

}
