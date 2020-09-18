import com.example.app.AppDelegate
import kotlinx.cinterop.autoreleasepool
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.toCStringArray
import platform.Foundation.NSStringFromClass
import platform.UIKit.UIApplicationMain

fun main(args: Array<String>) {
    memScoped {
        autoreleasepool {
            UIApplicationMain(
                argc = args.size + 1,
                argv = arrayOf("konan", *args).toCStringArray(memScope),
                principalClassName = null,
                delegateClassName = NSStringFromClass(AppDelegate)
            )
        }
    }
}
