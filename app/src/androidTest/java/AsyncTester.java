import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;


import com.udacity.gradle.builditbigger.MainActivity;
import com.udacity.gradle.builditbigger.R;


import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AsyncTester {
    @Rule
    public ActivityTestRule<MainActivity> mActivitiyTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);



    @Test
    public void clickingButton_GetsResults(){



        Espresso.onView(ViewMatchers.withId(R.id.joke_btn)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(ViewMatchers.withId(R.id.joke_btn)).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.tv_joke_holder)).check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.withText(""))));


    }
    }




