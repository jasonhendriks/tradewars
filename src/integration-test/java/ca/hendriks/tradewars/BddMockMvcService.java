package ca.hendriks.tradewars;

import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.SharedHttpSessionConfigurer.sharedHttpSession;

public class BddMockMvcService {

    private final MockMvc mockMvc;

    public BddMockMvcService(final WebApplicationContext webApplicationContext) {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(sharedHttpSession())
                .build();
    }

    public MvcResult postFile(final String uri, final String filename)
            throws Exception {
        final Path path = Paths.get(filename);
        final byte[] bytes = Files.readAllBytes(path);

        final MockMultipartFile file = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                bytes
        );

        return mockMvc
                .perform(multipart(uri)
                        .file(file))
                .andExpect(status().isOk())
                .andReturn();
    }

    public MvcResult get(final String uri, final MultiValueMap<String, String> params) {
        try {
            return mockMvc.perform(MockMvcRequestBuilders
                            .get(uri)
                            .params(params)
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                            .accept(MediaType.TEXT_HTML))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }


}
