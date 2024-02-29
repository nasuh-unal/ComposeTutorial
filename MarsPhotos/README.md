Mars Photos
==================================

Mars Photos app is a demo app that shows actual images of Mars' surface. These images are
real-life photos from Mars captured by NASA's Mars rovers. The data is stored on a Web server
as a REST web service.

This app demonstrated the use of [Retrofit](https://square.github.io/retrofit/) to make REST requests to the web service, [kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization) to
handle the deserialization of the returned JSON to Kotlin data objects, and [Coil](https://coil-kt.github.io/coil/) to load images by URL.

Ne öğreneceksiniz
-----------------
- REST web servisi nedir.
- İnternet üzerindeki bir REST web servisine bağlanmak ve bir yanıt almak için Retrofit kütüphanesinin nasıl kullanılacağı.
- JSON yanıtını bir veri nesnesine ayrıştırmak için Serialization (kotlinx.serialization) kütüphanesinin nasıl kullanılacağı.

Ne yapacaksınız
---------------
- Bir web hizmeti API isteği yapmak ve yanıtı işlemek için bir başlangıç uygulamasını değiştirin.
- Retrofit kütüphanesini kullanarak uygulamanız için bir data katmanı oluşturun.
- Web hizmetinden gelen JSON yanıtını kotlinx.serialization kütüphanesi ile uygulamanızın veri nesneleri listesine ayrıştırın ve UI durumuna ekleyin.
- Kodu basitleştirmek için Retrofit'in coroutine desteğini kullanın.

Notlarım
=================================

Web services and Retrofit
---------------

Mars fotoğrafları verileri bir web sunucusunda saklanır. Bu verileri uygulamanıza aktarmak için bir bağlantı kurmanız ve internet üzerindeki sunucu ile iletişim kurmanız gerekir.

<p align="center">
<img  src="https://github.com/nasuh-unal/ComposeTutorial/assets/88931522/8cce3e38-0711-42dd-9644-eafe5e9fd1c4"  width=500 height=300>

Günümüzde çoğu web sunucusu, web hizmetlerini REST olarak bilinen ve açılımı REpresentational State Transfer olan ortak bir durumsuz web mimarisi kullanarak çalıştırmaktadır. Bu mimariyi sunan web hizmetleri RESTful hizmetler olarak bilinir.

İstekler RESTful web hizmetlerine standart bir şekilde, Uniform Resource Identifiers (URI'ler) aracılığıyla yapılır. Bir URI, sunucudaki bir kaynağı, yerini veya ona nasıl erişileceğini belirtmeden adıyla tanımlar. 

URL (Tekdüzen Kaynak Konum Belirleyici), bir kaynağın nerede bulunduğunu ve ona ulaşma mekanizmasını belirten bir URI alt kümesidir.

Bu URL'ler, ağdan Hypertext Transfer Protocol (http:) aracılığıyla elde edilebilen /realestate veya /photos gibi tanımlanmış bir kaynağa başvurur. Bu codelab'de /photos uç noktasını kullanıyorsunuz. Uç nokta, bir sunucu üzerinde çalışan bir web hizmetine erişmenizi sağlayan bir URL'dir.

Web service request
---------------

Her web hizmeti isteği bir URI içerir ve Chrome gibi web tarayıcıları tarafından kullanılan aynı HTTP protokolü kullanılarak sunucuya aktarılır. HTTP istekleri, sunucuya ne yapması gerektiğini söyleyen bir işlem içerir.

Yaygın HTTP işlemleri şunları içerir:

- Sunucu verilerini almak için GET.
- Sunucuda yeni veri oluşturmak için POST.
- Sunucudaki mevcut verileri güncellemek için PUT.
- Sunucudan veri silmek için DELETE.
  
Uygulamanız, Mars fotoğrafları bilgileri için sunucuya bir HTTP GET isteği gönderir ve ardından sunucu, uygulamanıza resim URL'lerini içeren bir yanıt döndürür.

<p align="center">
<img  src="https://github.com/nasuh-unal/ComposeTutorial/assets/88931522/8b199fc2-c7f1-4900-b847-f759877a34d2"  width=500 height=200>

<p align="center">
<img  src="https://github.com/nasuh-unal/ComposeTutorial/assets/88931522/91e4bcc6-3e31-4377-b33d-02269ce7edca"  width=500 height=200>

Bir web hizmetinden alınan yanıt, XML (eXtensible Markup Language) veya JSON (JavaScript Object Notation) gibi yaygın veri formatlarından birinde biçimlendirilir. JSON formatı, yapılandırılmış verileri anahtar-değer çiftleri halinde temsil eder. Bir uygulama REST API ile JSON kullanarak iletişim kurar ve bu konuda daha sonraki bir görevde daha fazla bilgi edineceksiniz.

Bu görevde, sunucuya bir ağ bağlantısı kuracak, sunucuyla iletişim kuracak ve bir JSON yanıtı alacaksınız. Sizin için önceden yazılmış bir backend sunucusu kullanacaksınız. Bu codelab'de, backend sunucusu ile iletişim kurmak için üçüncü taraf bir kütüphane olan Retrofit kütüphanesini kullanacaksınız.

Retrofit Library
---------------

Bu codelab'de RESTful Mars web hizmetiyle konuşmak için kullandığınız Retrofit kütüphanesi, iyi desteklenen ve bakımı yapılan kütüphanelere iyi bir örnektir. Bunu GitHub sayfasına bakarak ve açık ve kapalı sorunları (bazıları özellik talepleridir) inceleyerek anlayabilirsiniz. Geliştiriciler sorunları düzenli olarak çözüyor ve özellik taleplerine yanıt veriyorsa, kütüphane muhtemelen bakımlıdır ve uygulamada kullanmak için iyi bir adaydır. Kütüphane hakkında daha fazla bilgi edinmek için Retrofit belgelerine de başvurabilirsiniz.

Retrofit kütüphanesi REST arka ucu ile iletişim kurar. Kodu oluşturur, ancak ona ilettiğimiz parametrelere dayalı olarak web hizmeti için URI'leri sağlamanız gerekir. İlerleyen bölümlerde bu konu hakkında daha fazla bilgi edineceksiniz.

<p align="center">
<img  src="https://github.com/nasuh-unal/ComposeTutorial/assets/88931522/714afe10-9b75-43f1-8463-937cfb29e5d8"  width=500 height=200>

Connecting to the Internet
---------------

Mars web servisiyle konuşmak ve ham JSON yanıtını bir String olarak görüntülemek için Retrofit kütüphanesini kullanırsınız. Placeholder Text, döndürülen JSON yanıt dizesini veya bağlantı hatasını belirten bir mesajı görüntüler.

Retrofit, web servisinden gelen içeriğe dayalı olarak uygulama için bir ağ API'si oluşturur. Web servisinden verileri alır ve verilerin kodunun nasıl çözüleceğini ve String gibi nesneler biçiminde nasıl döndürüleceğini bilen ayrı bir dönüştürücü kütüphanesi aracılığıyla yönlendirir. Retrofit, XML ve JSON gibi popüler veri formatları için yerleşik destek içerir. Retrofit sonuç olarak, istekleri arka plan iş parçacıklarında çalıştırmak gibi kritik ayrıntılar da dahil olmak üzere, bu hizmeti sizin için çağıracak ve kullanılacak kodu oluşturur.

<p align="center">
<img  src="https://github.com/nasuh-unal/ComposeTutorial/assets/88931522/6eaf3486-5aa6-4520-81b6-ba3aea6efe96"  width=500 height=200>

Parse the JSON response with kotlinx.serialization
--------------------------------------------------

**JSON**
İstenen veriler genellikle XML veya JSON gibi yaygın veri formatlarından birinde biçimlendirilir. Her çağrı yapılandırılmış veri döndürür ve uygulamanızın veriyi response'dan okuyabilmesi için bu yapının ne olduğunu bilmesi gerekir.

Örneğin, bu uygulamada verileri https:// android-kotlin-fun-mars-server.appspot.com/photos sunucusundan alıyorsunuz. Bu URL'yi tarayıcıya girdiğinizde, Mars yüzeyinin ID'lerinin ve görüntü URL'lerinin bir listesini JSON biçiminde görürsünüz!

**Structure of sample JSON response**
<p align="center">
<img  src="https://github.com/nasuh-unal/ComposeTutorial/assets/88931522/4c281189-0345-4798-a7c7-2b88b2320014"  width=500 height=200>

Bir JSON yanıtının yapısı aşağıdaki özelliklere sahiptir:

JSON yanıtı, köşeli parantezlerle gösterilen bir dizidir. Dizi JSON objeleri içerir.
JSON objeleri küme parantezleriyle çevrelenir.
Her JSON nesnesi, virgülle ayrılmış bir dizi anahtar-değer çifti içerir.
İki nokta üst üste, bir çiftteki anahtar ve değeri ayırır.
İsimler tırnak işaretleriyle çevrelenir.
Değerler sayılar, dizeler, boolean, dizi, nesne (JSON nesnesi) veya null olabilir.

Uygulamanızda şu anda Mars web hizmetinden bir JSON yanıtı alıyorsunuz, bu harika bir başlangıç. Ancak görüntüleri görüntülemek için gerçekten ihtiyacınız olan şey Kotlin nesneleridir, büyük bir JSON dizesi değil. Bu işleme deserialization denir.

Serialization, bir uygulama tarafından kullanılan verilerin bir ağ üzerinden aktarılabilecek bir biçime dönüştürülmesi işlemidir. Serialization tersine, deserialization harici bir kaynaktan (sunucu gibi) veri okuma ve bunu bir çalışma zamanı nesnesine dönüştürme işlemidir. Her ikisi de ağ üzerinden veri alışverişi yapan çoğu uygulamanın temel bileşenleridir.

**kotlinx.serialization, bir JSON dizesini Kotlin nesnelerine dönüştüren kütüphane setleri sağlar.** Retrofit, Kotlin Serialization Converter ile çalışan topluluk tarafından geliştirilmiş bir üçüncü taraf kütüphanesi vardır.

Bu görevde, web hizmetinden gelen JSON yanıtını Mars fotoğraflarını temsil eden kullanışlı Kotlin nesnelerine ayrıştırmak için kotlinx.serialization kütüphanesini kullanacaksınız. Uygulamayı, ham JSON'u görüntülemek yerine döndürülen Mars fotoğraflarının sayısını gösterecek şekilde değiştiriyorsunuz.

@SerialName Annotation
----------------------

Bazen JSON yanıtındaki anahtar adları, Kotlin özelliklerini kafa karıştırıcı hale getirebilir veya önerilen kodlama stiline uymayabilir. Örneğin, JSON dosyasında img_src anahtarında alt çizgi kullanılırken, Kotlin'in özellikler için kullandığı kural büyük ve küçük harflerdir (camel case).

Veri sınıfınızda JSON yanıtındaki anahtar adlarından farklı değişken adları kullanmak için @SerialName ek açıklamasını kullanın. Aşağıdaki örnekte, veri sınıfındaki değişkenin adı imgSrc'dir. Değişken, @SerialName(value = "img_src") kullanılarak JSON özniteliği img_src ile eşleştirilebilir.

REST web servisleri
-------------------
- Web servisi, uygulamanızın istekte bulunmasını ve verileri geri almasını sağlayan, internet üzerinden sunulan yazılım tabanlı bir işlevdir.
- Yaygın web hizmetleri bir REST mimarisi kullanır. REST mimarisi sunan web servisleri RESTful servisler olarak bilinir. RESTful web servisleri, standart web bileşenleri ve protokolleri kullanılarak oluşturulur.
- Bir REST web servisine URI'ler aracılığıyla standartlaştırılmış bir şekilde istekte bulunursunuz.
- Bir web servisini kullanmak için uygulamanın bir ağ bağlantısı kurması ve servisle iletişim kurması gerekir. Ardından uygulama, yanıt verilerini almalı ve uygulamanın kullanabileceği bir biçime dönüştürmelidir.
- Retrofit kütüphanesi, uygulamanızın bir REST web servisine istekte bulunmasını sağlayan bir istemci kütüphanesidir.
- Retrofit'e web servisine gönderdiği ve web servisinden geri aldığı verilerle ne yapacağını söylemek için dönüştürücüleri kullanın. Örneğin, ScalarsConverter web hizmeti verilerini bir String veya başka bir ilkel olarak ele alır.
- Uygulamanızın internete bağlantı kurmasını sağlamak için Android manifestosuna "android.permission.INTERNET" iznini ekleyin.
- Lazy initialization, bir nesnenin oluşturulmasını ilk kez kullanılacağı zamana devreder. Referans oluşturur ancak nesneyi oluşturmaz. Bir objeye ilk kez erişildiğinde, bir referans oluşturulur ve bundan sonraki her seferinde kullanılır.

JSON parsing
---------------------
- Bir web servisinden gelen yanıt genellikle yapılandırılmış verileri temsil etmek için yaygın bir format olan JSON ile biçimlendirilir.
- JSON nesnesi, anahtar-değer çiftlerinden oluşan bir koleksiyondur.
- JSON nesneleri koleksiyonu bir JSON dizisidir. Bir web servisinden yanıt olarak bir JSON dizisi alırsınız.
- Bir anahtar-değer çiftindeki anahtarlar **tırnak işaretleriyle** çevrelenir. Değerler sayılar veya dizeler olabilir.
- Kotlin'de, veri serileştirme araçları kotlinx.serialization adlı ayrı bir bileşende mevcuttur. Kotlinx.serialization, bir JSON dizesini Kotlin nesnelerine dönüştüren kütüphane setleri sağlar.
- Retrofit için topluluk tarafından geliştirilen bir Kotlin Serileştirme Dönüştürücü kütüphanesi vardır: retrofit2-kotlinx-serialization-converter.
- Kotlinx.serialization, bir JSON yanıtındaki anahtarları bir veri nesnesindeki aynı ada sahip özelliklerle eşleştirir.
- Bir anahtar için farklı bir özellik adı kullanmak için, bu özelliğe @SerialName ek açıklamasını ve JSON anahtar değerini ekleyin.

