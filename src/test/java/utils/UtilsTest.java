package utils;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class UtilsTest {

    @Test
    public void null_parameter_returns_empty_list(){
        List<Long> test = Utils.getCorrectIdList(null);

        int result = test.size();

        assertThat(result).isEqualTo(0);
    }

    @Test
    public void empty_list_returns_empty_list(){
        List<Long> test = Utils.getCorrectIdList(Arrays.asList());

        int result = test.size();

        assertThat(result).isEqualTo(0);
    }

    @Test
    public void correct_list_returns_list_of_long() {
        List<Long> test = Utils.getCorrectIdList(Arrays.asList("[\"0\"", "\"1\"", "\"2\"]"));

        List<Long> expectedList = Arrays.asList(0L, 1L, 2L);

        boolean expectedResult = true;

        boolean result = test.size() == expectedList.size() && test.containsAll(expectedList);

        assertThat(result).isEqualTo(true);
    }

    @Test(expected=NumberFormatException.class)
    public void bad_format_list_returns_number_exception() {
        Utils.getCorrectIdList(Arrays.asList("a", "b", "c"));
    }

    @Test(expected=NumberFormatException.class)
    public void partial_bad_format_list_returns_number_exception() {
        Utils.getCorrectIdList(Arrays.asList("[\"0\"", "\"1\"", "\"2\"]", "v"));
    }

    @Test
    public void list_of_numbers_returns_list_of_long() {
        List<Long> test = Utils.getCorrectIdList(Arrays.asList("1", "42", "300", "74"));

        List<Long> expectedList = Arrays.asList(1L, 42L, 300L, 74L);

        boolean expectedResult = true;

        boolean result = test.size() == expectedList.size() && test.containsAll(expectedList);

        assertThat(result).isEqualTo(true);
    }

}
