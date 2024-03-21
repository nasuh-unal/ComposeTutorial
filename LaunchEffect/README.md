Dessert Clicker app
=====================

Code for Android Basics with Compose Codelab.

Introduction
------------

Dessert Clicker is a game about making desserts.

Press the button, make a dessert, earn the big bucks.

You use this app in the course to explore the Android lifecycle and log messages to
the Android console (Logcat).


Activity Lifecycle
---------------

** onCreate()

-	Activity yaşam döngüsünde sadece bir kez çağırılır.
- Sistem uygulamayı oluştrduğunda.

** onStart() **

- OnCreate() metodundan sonra çağırılır.
- Activity'nizi başlatmak için yalnızca bir kez çağrılan onCreate() yönteminin aksine, onStart() yöntemi sistem tarafından activity'nizin yaşam döngüsü içinde birçok kez çağrılabilir.
- Uygulamayı ekranda görünür hale getirir, ancak kullanıcı henüz uygulama ile etkileşime girmez.

** onResume() **

- Uygulamayı ön plana çıkarır ve kullanıcı artık onunla etkileşime girebilir.
- OnResume() çağrıldığında, uygulama kullanıcı odağını kazanır; yani kullanıcı uygulamayla etkileşime girebilir.

** onRestart() ** 

-	onRestart() metodu aktivite zaten oluşturulmuşsa yani destroy edilmeden arka plana atılmışsa tekrar uygulamaya geri dönüldüğünde çağırılır.

** onStop() **

- OnStop() işlevinden sonra, uygulama artık ekranda görünmez. Aktivite durdurulmuş olsa da, Activity nesnesi arka planda hala bellektedir.
- OnStop() işlevinden sonra uygulama artık görünmez olur.

** onPause **

- onPause() işlevinden sonra odak kaybolur.

** My Notes **

- Odaklanma ve görünürlük arasındaki fark önemlidir. Bir aktivite ekranda kısmen görünür olabilir ancak kullanıcı odağına sahip olmayabilir.

- Bazı durumlarda odak uygulamada olmayabilir bu gibi durumlarda onPause() çağırılır fakat onStop() çağırılmaz çünkü ekranda bir kısmı hala görünür durumdadır. onPause() metodu içerisindeki kodlar hafif tutulmalıdır çünkü bir telefon görüşmesi yaptığınızda buradaki kodlar çalışıyor duruma gelir ve telefon görüşmesi sırasında onPause() metodu çalışır durumda olur ve kullanıcı kesintiye uğramış bir ekran görmez fakat çok fazla gerçekleştireceği durumlar var ise uygulama patlayabilir.

- Hem onResume() hem de onPause() odakla ilgilidir. Aktivite odak kazandığında onResume() yöntemi çağrılır ve aktivite odağı kaybettiğinde onPause() yöntemi çağrılır.

- Ekran döndürüldüğü zaman onDestroy() metodu çağırılır ve bunda göre uygulamanızı konfigüre etmezseniz data kaybında uğrayabilirsiniz.

- Compose'a bir nesnenin durumunu izlemesi gerektiğini belirtmek için, nesnenin State veya MutableState türünde olması gerekir. State türü değişmezdir ve yalnızca okunabilir. MutableState türü değişebilirdir ve okuma ve yazmaya izin verir. Yani bir değer değiştiğinde composable fonksiyonun tekrar güncelleme yapmasını sağlamak için state ve mutable state ifadelerini kullanmalıyız.

- Bir güncelleme olduğunda bunu izlemesi için mutableState ifadeseyle belirtiyorduk fakat compose fonkiyonu her güncellendiğinde yeniden oluşur ve içerikleri sıfırlar bunu da remember ifadesiyle çözeriz

- Android OS aktiviteyi yok edip yeniden oluşturduğunda ihtiyacınız olan değerleri kaydetmek için rememberSaveable fonksiyonunu kullanırsınız.





