package com.shelfService.shelfSyncBE.service;

import com.shelfService.shelfSyncBE.entity.Book;
import com.shelfService.shelfSyncBE.entity.ListElement;
import com.shelfService.shelfSyncBE.entity.Reader;
import com.shelfService.shelfSyncBE.entity.User;
import com.shelfService.shelfSyncBE.events.progress.UpdateElementEvent;
import com.shelfService.shelfSyncBE.repository.BookRepository;
import com.shelfService.shelfSyncBE.repository.ListElementRepository;
import com.shelfService.shelfSyncBE.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListElementService {
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    ListElementRepository listElementRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;

    public ListElement getElementById(Integer elementId) throws Exception{
        Optional<ListElement> element = listElementRepository.findById(elementId);
        if (element.isEmpty())
            throw new Exception("{getElementById} - Cannot find element");
        return element.get();
    }

    public ListElement createElement(Integer bookId, Integer userId, String progress, Integer currentPages) throws Exception {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        User user = userRepository.findByUid(userId);

        if(user==null)
            throw new Exception("{createBookProgress} - User not found");
        if(optionalBook.isEmpty())
            throw new Exception("{createBookProgress} - Could not find book");

        if(listElementRepository.findByUserAndBook((Reader) user,optionalBook.get())!=null)
            throw new Exception("{createBookProgress} - Entry already exists for uid "+ userId +" and bookid " + bookId);

        if (!isValidProgress(progress)) {
            throw new Exception("{createBookProgress} - Invalid progress");
        }

        ListElement element = new ListElement(user, optionalBook.get(), progress, currentPages);

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
//        applicationContext.publishEvent(
//                new UpdateElementEvent(this, elementId,newElem.getBook().getTitle(), "wtr", "nf", currentPages, 200)
//        );
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
