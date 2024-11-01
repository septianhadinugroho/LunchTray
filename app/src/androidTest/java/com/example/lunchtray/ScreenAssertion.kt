import androidx.navigation.NavController
import org.junit.Assert

fun NavController.assertCurrentScreenName(expectedScreenName:String){
    Assert.assertEquals(expectedScreenName, currentBackStackEntry?.destination?.route)
}