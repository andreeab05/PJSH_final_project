package com.shelfService.shelfSyncBE.service;

import com.shelfService.shelfSyncBE.entity.Book;
import com.shelfService.shelfSyncBE.entity.ListElement;
import com.shelfService.shelfSyncBE.repository.BookRepository;
import com.shelfService.shelfSyncBE.repository.ListElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListElementService {
    @Autowired
    ListElementRepository listElementRepository;

    @Autowired
    BookRepository bookRepository;

    public ListElement getElementById(Integer elementId) throws Exception{
        Optional<ListElement> element = listElementRepository.findById(elementId);
        if (element.isEmpty())
            throw new Exception("{getElementById} - Cannot find element");
        return element.get();
    }

    public ListElement createElement(Integer bookId, String progress, Integer currentPages) throws Exception {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        //User user = userRepository.findByUid(addElementDTO.getUid());

//        if(user==null)
//            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        if(optionalBook.isEmpty())
            throw new Exception("{createElement} - Could not find book");

//        if(listElementRepository.findByUserAndBook(user,optionalBook.get())!=null)
//            return new ResponseEntity<>("Entry already exists for uid and bookid", HttpStatus.BAD_REQUEST);

        // Validate progress value (optional)
        if (!isValidProgress(progress)) {
            throw new Exception("{createElement} - Invalid progress");
        }

        ListElement element = new ListElement(optionalBook.get(), progress, currentPages);

        listElementRepository.save(element);
        return element;
    }

    public ListElement updateElement(Integer elementId, String progress, Integer currentPages) throws Exception{
        Optional<ListElement> element = listElementRepository.findById(elementId);

        if (element.isEmpty())
            throw new Exception("{updateElement} - Could not find element");

        // Validate progress value (optional)
        if (!isValidProgress(progress))
            throw new Exception("{updateElement} - Invalid progress");

        element.get().setProgress(progress);
        element.get().setCurrent_pages(currentPages);
        ListElement newElem = listElementRepository.save(element.get());
        return newElem;
    }

    public void deleteElementById(Integer elementId) throws Exception{
        Optional<ListElement> element = listElementRepository.findById(elementId);
        if(element.isEmpty())
            throw new Exception("{deleteElementById} - Could not find list element");
        listElementRepository.deleteById(elementId);
    }

    // Utility method to validate progress value (optional)
    private boolean isValidProgress(String progress) {
        // Assuming progress can only be one of these values
        return progress.equals("dnf") || progress.equals("wtr") || progress.equals("read") || progress.equals("cr") || progress.equals("nr");
    }

}
