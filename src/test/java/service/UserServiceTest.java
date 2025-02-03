package service;

import com.shelfService.shelfSyncBE.entity.Author;
import com.shelfService.shelfSyncBE.entity.Reader;
import com.shelfService.shelfSyncBE.entity.User;
import com.shelfService.shelfSyncBE.repository.UserRepository;
import com.shelfService.shelfSyncBE.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private Reader mockReader;

    @BeforeEach
    void setUp() {
        mockReader = new Reader();
        mockReader.setUid(1);
        mockReader.setUsername("testUser");
        mockReader.setDescription("Initial profile description");
    }

    @Test
    void testGetUserByUid() {
        when(userRepository.findByUid(1)).thenReturn(mockReader);

        Reader result = (Reader) userService.getUserByUid(1);

        assertNotNull(result);
        assertEquals("testUser", result.getUsername());
        assertEquals("Initial profile description", result.getDescription());

        verify(userRepository, times(1)).findByUid(1);
    }

    @Test
    void testUpdateDescriptionByUid_Success() {
        when(userRepository.findByUid(1)).thenReturn(mockReader);

        String result = userService.updateDescriptionByUid(1, "Updated description");

        assertEquals("User description updated successfully", result);
        assertEquals("Updated description", mockReader.getDescription());

        verify(userRepository, times(1)).save(mockReader);
    }

    @Test
    void testUpdateDescriptionByUid_UserNotFound() {
        when(userRepository.findByUid(3)).thenReturn(null);

        String result = userService.updateDescriptionByUid(3, "New description");

        assertEquals("No user found", result);
        verify(userRepository, never()).save(any());
    }
    @Test
    void testCreateReader() {
        Reader mockReader = new Reader();
        mockReader.setUsername("readerUser");
        mockReader.setDescription("Reader description");

        when(userRepository.save(any(Reader.class))).thenReturn(mockReader);

        User result = userService.createReader("readerUser", "Reader description");

        assertNotNull(result);
        assertTrue(result instanceof Reader);
        assertEquals("readerUser", result.getUsername());
        assertEquals("Reader description", result.getDescription());

        verify(userRepository, times(1)).save(any(Reader.class));
    }

    @Test
    void testCreateAuthor() {
        Author mockAuthor = new Author();
        mockAuthor.setUsername("authorUser");
        mockAuthor.setDescription("Author description");

        when(userRepository.save(any(Author.class))).thenReturn(mockAuthor);

        User result = userService.createAuthor("authorUser", "Author description");

        assertNotNull(result);
        assertTrue(result instanceof Author);
        assertEquals("authorUser", result.getUsername());
        assertEquals("Author description", result.getDescription());

        verify(userRepository, times(1)).save(any(Author.class));
    }

    @Test
    void testDeleteUserByUid_Success() {
        when(userRepository.findByUid(1)).thenReturn(mockReader);

        String result = userService.deleteUserByUid(1);

        assertEquals("User deleted successfully", result);
        verify(userRepository, times(1)).deleteByUid(1);
    }

    @Test
    void testDeleteUserByUid_UserNotFound() {
        when(userRepository.findByUid(3)).thenReturn(null);

        String result = userService.deleteUserByUid(3);

        assertEquals("User not found", result);
        verify(userRepository, never()).deleteByUid(anyInt());
    }
}