package espacial.datos;

import espacial.utiles.Nombre;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Catálogo de nombres de personalidades.
 *
 * Adaptado a partir de:
 *      https://github.com/docker/engine/blob/master/pkg/namesgenerator/names-generator.go
 *      "Docker, starting from 0.7.x, generates names from notable scientists and hackers."
 */
public abstract class NombresDePersonalidades extends LinkedList<Nombre> {

    private static final Nombre[] NOMBRES = new Nombre[]{

            Nombre.es("Agnesi")
                    .porque("Maria Gaetana Agnesi - Italian mathematician, philosopher, theologian and humanitarian. She was the first woman to write a mathematics handbook and the first woman appointed as a Mathematics Professor at a University. https://en.wikipedia.org/wiki/Maria_Gaetana_Agnesi")
                    .obtener(),

            Nombre.es("Albattani")
                    .porque("Muhammad ibn Jābir al-Ḥarrānī al-Battānī was a founding father of astronomy. https://en.wikipedia.org/wiki/Mu%E1%B8%A5ammad_ibn_J%C4%81bir_al-%E1%B8%A4arr%C4%81n%C4%AB_al-Batt%C4%81n%C4%AB")
                    .obtener(),

            Nombre.es("Allen")
                    .porque("Frances E. Allen, became the first female IBM Fellow in 1989. In 2006, she became the first female recipient of the ACM's Turing Award. https://en.wikipedia.org/wiki/Frances_E._Allen")
                    .obtener(),


            Nombre.es("Almeida")
                    .porque("June Almeida - Scottish virologist who took the first pictures of the rubella virus - https://en.wikipedia.org/wiki/June_Almeida")
                    .obtener(),

            Nombre.es("Antonelli")
                    .porque("Kathleen Antonelli, American computer programmer and one of the six original programmers of the ENIAC - https://en.wikipedia.org/wiki/Kathleen_Antonelli")
                    .obtener(),

            Nombre.es("Archimedes")
                    .porque("Archimedes was a physicist, engineer and mathematician who invented too many things to list them here. https://en.wikipedia.org/wiki/Archimedes")
                    .obtener(),

            Nombre.es("Ardinghelli")
                    .porque("Maria Ardinghelli - Italian translator, mathematician and physicist - https://en.wikipedia.org/wiki/Maria_Ardinghelli")
                    .obtener(),

            Nombre.es("Aryabhata")
                    .porque("Aryabhata - Ancient Indian mathematician-astronomer during 476-550 CE https://en.wikipedia.org/wiki/Aryabhata")
                    .obtener(),

            Nombre.es("Austin")
                    .porque("Wanda Austin - Wanda Austin is the President and CEO of The Aerospace Corporation, a leading architect for the US security space programs. https://en.wikipedia.org/wiki/Wanda_Austin")
                    .obtener(),

            Nombre.es("Babbage")
                    .porque("Charles Babbage invented the concept of a programmable computer. https://en.wikipedia.org/wiki/Charles_Babbage.")
                    .obtener(),

            Nombre.es("Banach")
                    .porque("Stefan Banach - Polish mathematician, was one of the founders of modern functional analysis. https://en.wikipedia.org/wiki/Stefan_Banach")
                    .obtener(),

            Nombre.es("Banzai")
                    .porque("Buckaroo Banzai and his mentor Dr. Hikita perfectd the 'oscillation overthruster', a device that allows one to pass through solid matter. - https://en.wikipedia.org/wiki/The_Adventures_of_Buckaroo_Banzai_Across_the_8th_Dimension")
                    .obtener(),

            Nombre.es("Bardeen")
                    .porque("John Bardeen co-invented the transistor - https://en.wikipedia.org/wiki/John_Bardeen")
                    .obtener(),

            Nombre.es("Bartik")
                    .porque("Jean Bartik, born Betty Jean Jennings, was one of the original programmers for the ENIAC computer. https://en.wikipedia.org/wiki/Jean_Bartik")
                    .obtener(),

            Nombre.es("Bassi")
                    .porque("Laura Bassi, the world's first female professor https://en.wikipedia.org/wiki/Laura_Bassi")
                    .obtener(),

            Nombre.es("Beaver")
                    .porque("Hugh Beaver, British engineer, founder of the Guinness Book of World Records https://en.wikipedia.org/wiki/Hugh_Beaver")
                    .obtener(),

            Nombre.es("Bell")
                    .porque("Alexander Graham Bell - an eminent Scottish-born scientist, inventor, engineer and innovator who is credited with inventing the first practical telephone - https://en.wikipedia.org/wiki/Alexander_Graham_Bell")
                    .obtener(),

            Nombre.es("Benz")
                    .porque("Karl Friedrich Benz - a German automobile engineer. Inventor of the first practical motorcar. https://en.wikipedia.org/wiki/Karl_Benz")
                    .obtener(),

            Nombre.es("Bhabha")
                    .porque("Homi J Bhabha - was an Indian nuclear physicist, founding director, and professor of physics at the Tata Institute of Fundamental Research. Colloquially known as 'father of Indian nuclear programme'- https://en.wikipedia.org/wiki/Homi_J._Bhabha")
                    .obtener(),

            Nombre.es("Bhaskara")
                    .porque("Bhaskara II - Ancient Indian mathematician-astronomer whose work on calculus predates Newton and Leibniz by over half a millennium - https://en.wikipedia.org/wiki/Bh%C4%81skara_II#Calculus")
                    .obtener(),

            Nombre.es("Black")
                    .porque("Sue Black - British computer scientist and campaigner. She has been instrumental in saving Bletchley Park, the site of World War II codebreaking - https://en.wikipedia.org/wiki/Sue_Black_(computer_scientist)")
                    .obtener(),

            Nombre.es("Blackburn")
                    .porque("Elizabeth Helen Blackburn - Australian-American Nobel laureate; best known for co-discovering telomerase. https://en.wikipedia.org/wiki/Elizabeth_Blackburn")
                    .obtener(),

            Nombre.es("Blackwell")
                    .porque("Elizabeth Blackwell - American doctor and first American woman to receive a medical degree - https://en.wikipedia.org/wiki/Elizabeth_Blackwell")
                    .obtener(),

            Nombre.es("Bohr")
                    .porque("Niels Bohr is the father of quantum theory. https://en.wikipedia.org/wiki/Niels_Bohr.")
                    .obtener(),

            Nombre.es("Booth")
                    .porque("Kathleen Booth, she's credited with writing the first assembly language. https://en.wikipedia.org/wiki/Kathleen_Booth")
                    .obtener(),

            Nombre.es("Borg")
                    .porque("Anita Borg - Anita Borg was the founding director of the Institute for Women and Technology (IWT). https://en.wikipedia.org/wiki/Anita_Borg")
                    .obtener(),

            Nombre.es("Bose")
                    .porque("Satyendra Nath Bose - He provided the foundation for Bose–Einstein statistics and the theory of the Bose–Einstein condensate. - https://en.wikipedia.org/wiki/Satyendra_Nath_Bose")
                    .obtener(),

            Nombre.es("Bouman")
                    .porque("Katherine Louise Bouman is an imaging scientist and Assistant Professor of Computer Science at the California Institute of Technology. She researches computational methods for imaging, and developed an algorithm that made possible the picture first visualization of a black hole using the Event Horizon Telescope. - https://en.wikipedia.org/wiki/Katie_Bouman")
                    .obtener(),

            Nombre.es("Boyd")
                    .porque("Evelyn Boyd Granville - She was one of the first African-American woman to receive a Ph.D. in mathematics; she earned it in 1949 from Yale University. https://en.wikipedia.org/wiki/Evelyn_Boyd_Granville")
                    .obtener(),

            Nombre.es("Brahmagupta")
                    .porque("Brahmagupta - Ancient Indian mathematician during 598-670 CE who gave rules to compute with zero - https://en.wikipedia.org/wiki/Brahmagupta#Zero")
                    .obtener(),

            Nombre.es("Brattain")
                    .porque("Walter Houser Brattain co-invented the transistor - https://en.wikipedia.org/wiki/Walter_Houser_Brattain")
                    .obtener(),

            Nombre.es("Brown")
                    .porque("Emmett Brown invented time travel. https://en.wikipedia.org/wiki/Emmett_Brown (thanks Brian Goff)")
                    .obtener(),

            Nombre.es("Buck")
                    .porque("Linda Brown Buck - American biologist and Nobel laureate best known for her genetic and molecular analyses of the mechanisms of smell. https://en.wikipedia.org/wiki/Linda_B._Buck")
                    .obtener(),

            Nombre.es("Burnell")
                    .porque("Dame Susan Jocelyn Bell Burnell - Northern Irish astrophysicist who discovered radio pulsars and was the first to analyse them. https://en.wikipedia.org/wiki/Jocelyn_Bell_Burnell")
                    .obtener(),

            Nombre.es("Cannon")
                    .porque("Annie Jump Cannon - pioneering female astronomer who classified hundreds of thousands of stars and created the system we use to understand stars today. https://en.wikipedia.org/wiki/Annie_Jump_Cannon")
                    .obtener(),

            Nombre.es("Carson")
                    .porque("Rachel Carson - American marine biologist and conservationist, her book Silent Spring and other writings are credited with advancing the global environmental movement. https://en.wikipedia.org/wiki/Rachel_Carson")
                    .obtener(),

            Nombre.es("Cartwright")
                    .porque("Dame Mary Lucy Cartwright - British mathematician who was one of the first to study what is now known as chaos theory. Also known for Cartwright's theorem which finds applications in signal processing. https://en.wikipedia.org/wiki/Mary_Cartwright")
                    .obtener(),

            Nombre.es("Cerf")
                    .porque("Vinton Gray Cerf - American Internet pioneer, recognised as one of 'the fathers of the Internet'. With Robert Elliot Kahn, he designed TCP and IP, the primary data communication protocols of the Internet and other computer networks. https://en.wikipedia.org/wiki/Vint_Cerf")
                    .obtener(),

            Nombre.es("Chandrasekhar")
                    .porque("Subrahmanyan Chandrasekhar - Astrophysicist known for his mathematical theory on different stages and evolution in structures of the stars. He has won nobel prize for physics - https://en.wikipedia.org/wiki/Subrahmanyan_Chandrasekhar")
                    .obtener(),

            Nombre.es("Chaplygin")
                    .porque("Sergey Alexeyevich Chaplygin (Russian: Серге́й Алексе́евич Чаплы́гин; April 5, 1869 – October 8, 1942) was a Russian and Soviet physicist, mathematician, and mechanical engineer. He is known for mathematical formulas such as Chaplygin's equation and for a hypothetical substance in cosmology called Chaplygin gas, named after him. https://en.wikipedia.org/wiki/Sergey_Chaplygin")
                    .obtener(),

            Nombre.es("Chatelet")
                    .porque("Émilie du Châtelet - French natural philosopher, mathematician, physicist, and author during the early 1730s, known for her translation of and commentary on Isaac Newton's book Principia containing basic laws of physics. https://en.wikipedia.org/wiki/%C3%89milie_du_Ch%C3%A2telet")
                    .obtener(),

            Nombre.es("Chatterjee")
                    .porque("Asima Chatterjee was an Indian organic chemist noted for her research on vinca alkaloids, development of drugs for treatment of epilepsy and malaria - https://en.wikipedia.org/wiki/Asima_Chatterjee")
                    .obtener(),

            Nombre.es("Chaum")
                    .porque("David Lee Chaum - American computer scientist and cryptographer. Known for his seminal contributions in the field of anonymous communication. https://en.wikipedia.org/wiki/David_Chaum")
                    .obtener(),

            Nombre.es("Chebyshev")
                    .porque("Pafnuty Chebyshev - Russian mathematician. He is known fo his works on probability, statistics, mechanics, analytical geometry and number theory https://en.wikipedia.org/wiki/Pafnuty_Chebyshev")
                    .obtener(),

            Nombre.es("Clarke")
                    .porque("Joan Clarke - Bletchley Park code breaker during the Second World War who pioneered techniques that remained top secret for decades. Also an accomplished numismatist https://en.wikipedia.org/wiki/Joan_Clarke")
                    .obtener(),

            Nombre.es("Cohen")
                    .porque("Bram Cohen - American computer programmer and author of the BitTorrent peer-to-peer protocol. https://en.wikipedia.org/wiki/Bram_Cohen")
                    .obtener(),

            Nombre.es("Colden")
                    .porque("Jane Colden - American botanist widely considered the first female American botanist - https://en.wikipedia.org/wiki/Jane_Colden")
                    .obtener(),

            Nombre.es("Cori")
                    .porque("Gerty Theresa Cori - American biochemist who became the third woman—and first American woman—to win a Nobel Prize in science, and the first woman to be awarded the Nobel Prize in Physiology or Medicine. Cori was born in Prague. https://en.wikipedia.org/wiki/Gerty_Cori")
                    .obtener(),

            Nombre.es("Cray")
                    .porque("Seymour Roger Cray was an American electrical engineer and supercomputer architect who designed a series of computers that were the fastest in the world for decades. https://en.wikipedia.org/wiki/Seymour_Cray")
                    .obtener(),

            Nombre.es("Curie")
                    .porque("Marie Curie discovered radioactivity. https://en.wikipedia.org/wiki/Marie_Curie.")
                    .obtener(),

            Nombre.es("Curran")
                    .porque("This entry reflects a husband and wife team who worked together: Joan Curran was a Welsh scientist who developed radar and invented chaff, a radar countermeasure. https://en.wikipedia.org/wiki/Joan_Curran. Samuel Curran was an Irish physicist who worked alongside his wife during WWII and invented the proximity fuse. https://en.wikipedia.org/wiki/Samuel_Curran")
                    .obtener(),

            Nombre.es("Darwin")
                    .porque("Charles Darwin established the principles of natural evolution. https://en.wikipedia.org/wiki/Charles_Darwin.")
                    .obtener(),

            Nombre.es("Davinci")
                    .porque("Leonardo Da Vinci invented too many things to list here. https://en.wikipedia.org/wiki/Leonardo_da_Vinci.")
                    .obtener(),

            Nombre.es("Dewdney")
                    .porque("A. K. (Alexander Keewatin) Dewdney, Canadian mathematician, computer scientist, author and filmmaker. Contributor to Scientific American's 'Computer Recreations' from 1984 to 1991. Author of Core War (program), The Planiverse, The Armchair Universe, The Magic Machine, The New Turing Omnibus, and more. https://en.wikipedia.org/wiki/Alexander_Dewdney")
                    .obtener(),

            Nombre.es("Dhawan")
                    .porque("Satish Dhawan - Indian mathematician and aerospace engineer, known for leading the successful and indigenous development of the Indian space programme. https://en.wikipedia.org/wiki/Satish_Dhawan")
                    .obtener(),

            Nombre.es("Diffie")
                    .porque("Bailey Whitfield Diffie - American cryptographer and one of the pioneers of public-key cryptography. https://en.wikipedia.org/wiki/Whitfield_Diffie")
                    .obtener(),

            Nombre.es("Dijkstra")
                    .porque("Edsger Wybe Dijkstra was a Dutch computer scientist and mathematical scientist. https://en.wikipedia.org/wiki/Edsger_W._Dijkstra.")
                    .obtener(),

            Nombre.es("Dirac")
                    .porque("Paul Adrien Maurice Dirac - English theoretical physicist who made fundamental contributions to the early development of both quantum mechanics and quantum electrodynamics. https://en.wikipedia.org/wiki/Paul_Dirac")
                    .obtener(),

            Nombre.es("Driscoll")
                    .porque("Agnes Meyer Driscoll - American cryptanalyst during World Wars I and II who successfully cryptanalysed a number of Japanese ciphers. She was also the co-developer of one of the cipher machines of the US Navy, the CM. https://en.wikipedia.org/wiki/Agnes_Meyer_Driscoll")
                    .obtener(),

            Nombre.es("Dubinsky")
                    .porque("Donna Dubinsky - played an integral role in the development of personal digital assistants (PDAs) serving as CEO of Palm, Inc. and co-founding Handspring. https://en.wikipedia.org/wiki/Donna_Dubinsky")
                    .obtener(),

            Nombre.es("Easley")
                    .porque("Annie Easley - She was a leading member of the team which developed software for the Centaur rocket stage and one of the first African-Americans in her field. https://en.wikipedia.org/wiki/Annie_Easley")
                    .obtener(),

            Nombre.es("Edison")
                    .porque("Thomas Alva Edison, prolific inventor https://en.wikipedia.org/wiki/Thomas_Edison")
                    .obtener(),

            Nombre.es("Einstein")
                    .porque("Albert Einstein invented the general theory of relativity. https://en.wikipedia.org/wiki/Albert_Einstein")
                    .obtener(),

            Nombre.es("Elbakyan")
                    .porque("Alexandra Asanovna Elbakyan (Russian: Алекса́ндра Аса́новна Элбакя́н) is a Kazakhstani graduate student, computer programmer, internet pirate in hiding, and the creator of the site Sci-Hub. Nature has listed her in 2016 in the top ten people that mattered in science, and Ars Technica has compared her to Aaron Swartz. - https://en.wikipedia.org/wiki/Alexandra_Elbakyan")
                    .obtener(),

            Nombre.es("Elgamal")
                    .porque("Taher A. ElGamal - Egyptian cryptographer best known for the ElGamal discrete log cryptosystem and the ElGamal digital signature scheme. https://en.wikipedia.org/wiki/Taher_Elgamal")
                    .obtener(),

            Nombre.es("Elion")
                    .porque("Gertrude Elion - American biochemist, pharmacologist and the 1988 recipient of the Nobel Prize in Medicine - https://en.wikipedia.org/wiki/Gertrude_Elion")
                    .obtener(),

            Nombre.es("Ellis")
                    .porque("James Henry Ellis - British engineer and cryptographer employed by the GCHQ. Best known for conceiving for the first time, the idea of public-key cryptography. https://en.wikipedia.org/wiki/James_H._Ellis")
                    .obtener(),

            Nombre.es("Engelbart")
                    .porque("Douglas Engelbart gave the mother of all demos: https://en.wikipedia.org/wiki/Douglas_Engelbart")
                    .obtener(),

            Nombre.es("Euclid")
                    .porque("Euclid invented geometry. https://en.wikipedia.org/wiki/Euclid")
                    .obtener(),

            Nombre.es("Euler")
                    .porque("Leonhard Euler invented large parts of modern mathematics. https://de.wikipedia.org/wiki/Leonhard_Euler")
                    .obtener(),

            Nombre.es("Faraday")
                    .porque("Michael Faraday - British scientist who contributed to the study of electromagnetism and electrochemistry. https://en.wikipedia.org/wiki/Michael_Faraday")
                    .obtener(),

            Nombre.es("Feistel")
                    .porque("Horst Feistel - German-born American cryptographer who was one of the earliest non-government researchers to study the design and theory of block ciphers. Co-developer of DES and Lucifer. Feistel networks, a symmetric structure used in the construction of block ciphers are named after him. https://en.wikipedia.org/wiki/Horst_Feistel")
                    .obtener(),

            Nombre.es("Fermat")
                    .porque("Pierre de Fermat pioneered several aspects of modern mathematics. https://en.wikipedia.org/wiki/Pierre_de_Fermat")
                    .obtener(),

            Nombre.es("Fermi")
                    .porque("Enrico Fermi invented the first nuclear reactor. https://en.wikipedia.org/wiki/Enrico_Fermi.")
                    .obtener(),

            Nombre.es("Feynman")
                    .porque("Richard Feynman was a key contributor to quantum mechanics and particle physics. https://en.wikipedia.org/wiki/Richard_Feynman")
                    .obtener(),

            Nombre.es("Franklin")
                    .porque("Benjamin Franklin is famous for his experiments in electricity and the invention of the lightning rod.")
                    .obtener(),

            Nombre.es("Gagarin")
                    .porque("Yuri Alekseyevich Gagarin - Soviet pilot and cosmonaut, best known as the first human to journey into outer space. https://en.wikipedia.org/wiki/Yuri_Gagarin")
                    .obtener(),

            Nombre.es("Galileo")
                    .porque("Galileo was a founding father of modern astronomy, and faced politics and obscurantism to establish scientific truth.  https://en.wikipedia.org/wiki/Galileo_Galilei")
                    .obtener(),

            Nombre.es("Galois")
                    .porque("Évariste Galois - French mathematician whose work laid the foundations of Galois theory and group theory, two major branches of abstract algebra, and the subfield of Galois connections, all while still in his late teens. https://en.wikipedia.org/wiki/%C3%89variste_Galois")
                    .obtener(),

            Nombre.es("Ganguly")
                    .porque("Kadambini Ganguly - Indian physician, known for being the first South Asian female physician, trained in western medicine, to graduate in South Asia. https://en.wikipedia.org/wiki/Kadambini_Ganguly")
                    .obtener(),

            Nombre.es("Gates")
                    .porque("William Henry 'Bill' Gates III is an American business magnate, philanthropist, investor, computer programmer, and inventor. https://en.wikipedia.org/wiki/Bill_Gates")
                    .obtener(),

            Nombre.es("Gauss")
                    .porque("Johann Carl Friedrich Gauss - German mathematician who made significant contributions to many fields, including number theory, algebra, statistics, analysis, differential geometry, geodesy, geophysics, mechanics, electrostatics, magnetic fields, astronomy, matrix theory, and optics. https://en.wikipedia.org/wiki/Carl_Friedrich_Gauss")
                    .obtener(),

            Nombre.es("Germain")
                    .porque("Marie-Sophie Germain - French mathematician, physicist and philosopher. Known for her work on elasticity theory, number theory and philosophy. https://en.wikipedia.org/wiki/Sophie_Germain")
                    .obtener(),

            Nombre.es("Goldberg")
                    .porque("Adele Goldberg, was one of the designers and developers of the Smalltalk language. https://en.wikipedia.org/wiki/Adele_Goldberg_(computer_scientist)")
                    .obtener(),

            Nombre.es("Goldstine")
                    .porque("Adele Goldstine, born Adele Katz, wrote the complete technical description for the first electronic digital computer, ENIAC. https://en.wikipedia.org/wiki/Adele_Goldstine")
                    .obtener(),

            Nombre.es("Goldwasser")
                    .porque("Shafi Goldwasser is a computer scientist known for creating theoretical foundations of modern cryptography. Winner of 2012 ACM Turing Award. https://en.wikipedia.org/wiki/Shafi_Goldwasser")
                    .obtener(),

            Nombre.es("Golick")
                    .porque("James Golick, all around gangster.")
                    .obtener(),

            Nombre.es("Goodall")
                    .porque("Jane Goodall - British primatologist, ethologist, and anthropologist who is considered to be the world's foremost expert on chimpanzees - https://en.wikipedia.org/wiki/Jane_Goodall")
                    .obtener(),

            Nombre.es("Gould")
                    .porque("Stephen Jay Gould was was an American paleontologist, evolutionary biologist, and historian of science. He is most famous for the theory of punctuated equilibrium - https://en.wikipedia.org/wiki/Stephen_Jay_Gould")
                    .obtener(),

            Nombre.es("Greider")
                    .porque("Carolyn Widney Greider - American molecular biologist and joint winner of the 2009 Nobel Prize for Physiology or Medicine for the discovery of telomerase. https://en.wikipedia.org/wiki/Carol_W._Greider")
                    .obtener(),

            Nombre.es("Grothendieck")
                    .porque("Alexander Grothendieck - German-born French mathematician who became a leading figure in the creation of modern algebraic geometry. https://en.wikipedia.org/wiki/Alexander_Grothendieck")
                    .obtener(),

            Nombre.es("Haibt")
                    .porque("Lois Haibt - American computer scientist, part of the team at IBM that developed FORTRAN - https://en.wikipedia.org/wiki/Lois_Haibt")
                    .obtener(),

            Nombre.es("Hamilton")
                    .porque("Margaret Hamilton - Director of the Software Engineering Division of the MIT Instrumentation Laboratory, which developed on-board flight software for the Apollo space program. https://en.wikipedia.org/wiki/Margaret_Hamilton_(scientist)")
                    .obtener(),

            Nombre.es("Haslett")
                    .porque("Caroline Harriet Haslett - English electrical engineer, electricity industry administrator and champion of women's rights. Co-author of British Standard 1363 that specifies AC power plugs and sockets used across the United Kingdom (which is widely considered as one of the safest designs). https://en.wikipedia.org/wiki/Caroline_Haslett")
                    .obtener(),

            Nombre.es("Hawking")
                    .porque("Stephen Hawking pioneered the field of cosmology by combining general relativity and quantum mechanics. https://en.wikipedia.org/wiki/Stephen_Hawking")
                    .obtener(),

            Nombre.es("Heisenberg")
                    .porque("Werner Heisenberg was a founding father of quantum mechanics. https://en.wikipedia.org/wiki/Werner_Heisenberg")
                    .obtener(),

            Nombre.es("Hellman")
                    .porque("Martin Edward Hellman - American cryptologist, best known for his invention of public-key cryptography in co-operation with Whitfield Diffie and Ralph Merkle. https://en.wikipedia.org/wiki/Martin_Hellman")
                    .obtener(),

            Nombre.es("Hermann")
                    .porque("Grete Hermann was a German philosopher noted for her philosophical work on the foundations of quantum mechanics. https://en.wikipedia.org/wiki/Grete_Hermann")
                    .obtener(),

            Nombre.es("Herschel")
                    .porque("Caroline Lucretia Herschel - German astronomer and discoverer of several comets. https://en.wikipedia.org/wiki/Caroline_Herschel")
                    .obtener(),

            Nombre.es("Hertz")
                    .porque("Heinrich Rudolf Hertz - German physicist who first conclusively proved the existence of the electromagnetic waves. https://en.wikipedia.org/wiki/Heinrich_Hertz")
                    .obtener(),

            Nombre.es("Heyrovsky")
                    .porque("Jaroslav Heyrovský was the inventor of the polarographic method, father of the electroanalytical method, and recipient of the Nobel Prize in 1959. His main field of work was polarography. https://en.wikipedia.org/wiki/Jaroslav_Heyrovsk%C3%BD")
                    .obtener(),

            Nombre.es("Hodgkin")
                    .porque("Dorothy Hodgkin was a British biochemist, credited with the development of protein crystallography. She was awarded the Nobel Prize in Chemistry in 1964. https://en.wikipedia.org/wiki/Dorothy_Hodgkin")
                    .obtener(),

            Nombre.es("Hofstadter")
                    .porque("Douglas R. Hofstadter is an American professor of cognitive science and author of the Pulitzer Prize and American Book Award-winning work Goedel, Escher, Bach: An Eternal Golden Braid in 1979. A mind-bending work which coined Hofstadter's Law: \"It always takes longer than you expect, even when you take into account Hofstadter's Law.\" https://en.wikipedia.org/wiki/Douglas_Hofstadter")
                    .obtener(),

            Nombre.es("Hoover")
                    .porque("Erna Schneider Hoover revolutionized modern communication by inventing a computerized telephone switching method. https://en.wikipedia.org/wiki/Erna_Schneider_Hoover")
                    .obtener(),

            Nombre.es("Hopper")
                    .porque("Grace Hopper developed the first compiler for a computer programming language and  is credited with popularizing the term 'debugging' for fixing computer glitches. https://en.wikipedia.org/wiki/Grace_Hopper")
                    .obtener(),

            Nombre.es("Hugle")
                    .porque("Frances Hugle, she was an American scientist, engineer, and inventor who contributed to the understanding of semiconductors, integrated circuitry, and the unique electrical principles of microscopic materials. https://en.wikipedia.org/wiki/Frances_Hugle")
                    .obtener(),

            Nombre.es("Hypatia")
                    .porque("Hypatia - Greek Alexandrine Neoplatonist philosopher in Egypt who was one of the earliest mothers of mathematics - https://en.wikipedia.org/wiki/Hypatia")
                    .obtener(),

            Nombre.es("Ishizaka")
                    .porque("Teruko Ishizaka - Japanese scientist and immunologist who co-discovered the antibody class Immunoglobulin E. https://en.wikipedia.org/wiki/Teruko_Ishizaka")
                    .obtener(),

            Nombre.es("Jackson")
                    .porque("Mary Jackson, American mathematician and aerospace engineer who earned the highest title within NASA's engineering department - https://en.wikipedia.org/wiki/Mary_Jackson_(engineer)")
                    .obtener(),

            Nombre.es("Jang")
                    .porque("Yeong-Sil Jang was a Korean scientist and astronomer during the Joseon Dynasty; he invented the first metal printing press and water gauge. https://en.wikipedia.org/wiki/Jang_Yeong-sil")
                    .obtener(),

            Nombre.es("Jennings")
                    .porque("Betty Jennings - one of the original programmers of the ENIAC. https://en.wikipedia.org/wiki/ENIAC - https://en.wikipedia.org/wiki/Jean_Bartik")
                    .obtener(),

            Nombre.es("Jepsen")
                    .porque("Mary Lou Jepsen, was the founder and chief technology officer of One Laptop Per Child (OLPC), and the founder of Pixel Qi. https://en.wikipedia.org/wiki/Mary_Lou_Jepsen")
                    .obtener(),

            Nombre.es("Johnson")
                    .porque("Katherine Coleman Goble Johnson - American physicist and mathematician contributed to the NASA. https://en.wikipedia.org/wiki/Katherine_Johnson")
                    .obtener(),

            Nombre.es("Joliot")
                    .porque("Irène Joliot-Curie - French scientist who was awarded the Nobel Prize for Chemistry in 1935. Daughter of Marie and Pierre Curie. https://en.wikipedia.org/wiki/Ir%C3%A8ne_Joliot-Curie")
                    .obtener(),

            Nombre.es("Jones")
                    .porque("Karen Spärck Jones came up with the concept of inverse document frequency, which is used in most search engines today. https://en.wikipedia.org/wiki/Karen_Sp%C3%A4rck_Jones")
                    .obtener(),

            Nombre.es("Kalam")
                    .porque("A. P. J. Abdul Kalam - is an Indian scientist aka Missile Man of India for his work on the development of ballistic missile and launch vehicle technology - https://en.wikipedia.org/wiki/A._P._J._Abdul_Kalam")
                    .obtener(),

            Nombre.es("Kapitsa")
                    .porque("Sergey Petrovich Kapitsa (Russian: Серге́й Петро́вич Капи́ца; 14 February 1928 – 14 August 2012) was a Russian physicist and demographer. He was best known as host of the popular and long-running Russian scientific TV show, Evident, but Incredible. His father was the Nobel laureate Soviet-era physicist Pyotr Kapitsa, and his brother was the geographer and Antarctic explorer Andrey Kapitsa. - https://en.wikipedia.org/wiki/Sergey_Kapitsa")
                    .obtener(),

            Nombre.es("Kare")
                    .porque("Susan Kare, created the icons and many of the interface elements for the original Apple Macintosh in the 1980s, and was an original employee of NeXT, working as the Creative Director. https://en.wikipedia.org/wiki/Susan_Kare")
                    .obtener(),

            Nombre.es("Keldysh")
                    .porque("Mstislav Keldysh - a Soviet scientist in the field of mathematics and mechanics, academician of the USSR Academy of Sciences (1946), President of the USSR Academy of Sciences (1961–1975), three times Hero of Socialist Labor (1956, 1961, 1971), fellow of the Royal Society of Edinburgh (1968). https://en.wikipedia.org/wiki/Mstislav_Keldysh")
                    .obtener(),

            Nombre.es("Keller")
                    .porque("Mary Kenneth Keller, Sister Mary Kenneth Keller became the first American woman to earn a PhD in Computer Science in 1965. https://en.wikipedia.org/wiki/Mary_Kenneth_Keller")
                    .obtener(),

            Nombre.es("Kepler")
                    .porque("Johannes Kepler, German astronomer known for his three laws of planetary motion - https://en.wikipedia.org/wiki/Johannes_Kepler")
                    .obtener(),

            Nombre.es("Khayyam")
                    .porque("Omar Khayyam - Persian mathematician, astronomer and poet. Known for his work on the classification and solution of cubic equations, for his contribution to the understanding of Euclid's fifth postulate and for computing the length of a year very accurately. https://en.wikipedia.org/wiki/Omar_Khayyam")
                    .obtener(),

            Nombre.es("Khorana")
                    .porque("Har Gobind Khorana - Indian-American biochemist who shared the 1968 Nobel Prize for Physiology - https://en.wikipedia.org/wiki/Har_Gobind_Khorana")
                    .obtener(),

            Nombre.es("Kilby")
                    .porque("Jack Kilby invented silicone integrated circuits and gave Silicon Valley its name. - https://en.wikipedia.org/wiki/Jack_Kilby")
                    .obtener(),

            Nombre.es("Kirch")
                    .porque("Maria Kirch - German astronomer and first woman to discover a comet - https://en.wikipedia.org/wiki/Maria_Margarethe_Kirch")
                    .obtener(),

            Nombre.es("Knuth")
                    .porque("Donald Knuth - American computer scientist, author of 'The Art of Computer Programming' and creator of the TeX typesetting system. https://en.wikipedia.org/wiki/Donald_Knuth")
                    .obtener(),

            Nombre.es("Kowalevski")
                    .porque("Sophie Kowalevski - Russian mathematician responsible for important original contributions to analysis, differential equations and mechanics - https://en.wikipedia.org/wiki/Sofia_Kovalevskaya")
                    .obtener(),

            Nombre.es("Lalande")
                    .porque("Marie-Jeanne de Lalande - French astronomer, mathematician and cataloguer of stars - https://en.wikipedia.org/wiki/Marie-Jeanne_de_Lalande")
                    .obtener(),

            Nombre.es("Lamarr")
                    .porque("Hedy Lamarr - Actress and inventor. The principles of her work are now incorporated into modern Wi-Fi, CDMA and Bluetooth technology. https://en.wikipedia.org/wiki/Hedy_Lamarr")
                    .obtener(),

            Nombre.es("Lamport")
                    .porque("Leslie B. Lamport - American computer scientist. Lamport is best known for his seminal work in distributed systems and was the winner of the 2013 Turing Award. https://en.wikipedia.org/wiki/Leslie_Lamport")
                    .obtener(),

            Nombre.es("Leakey")
                    .porque("Mary Leakey - British paleoanthropologist who discovered the first fossilized Proconsul skull - https://en.wikipedia.org/wiki/Mary_Leakey")
                    .obtener(),

            Nombre.es("Leavitt")
                    .porque("Henrietta Swan Leavitt - she was an American astronomer who discovered the relation between the luminosity and the period of Cepheid variable stars. https://en.wikipedia.org/wiki/Henrietta_Swan_Leavitt")
                    .obtener(),

            Nombre.es("Lederberg")
                    .porque("Esther Miriam Zimmer Lederberg - American microbiologist and a pioneer of bacterial genetics. https://en.wikipedia.org/wiki/Esther_Lederberg")
                    .obtener(),

            Nombre.es("Lehmann")
                    .porque("Inge Lehmann - Danish seismologist and geophysicist. Known for discovering in 1936 that the Earth has a solid inner core inside a molten outer core. https://en.wikipedia.org/wiki/Inge_Lehmann")
                    .obtener(),

            Nombre.es("Lewin")
                    .porque("Daniel Lewin - Mathematician, Akamai co-founder, soldier, 9/11 victim-- Developed optimization techniques for routing traffic on the internet. Died attempting to stop the 9-11 hijackers. https://en.wikipedia.org/wiki/Daniel_Lewin")
                    .obtener(),

            Nombre.es("Lichterman")
                    .porque("Ruth Lichterman - one of the original programmers of the ENIAC. https://en.wikipedia.org/wiki/ENIAC - https://en.wikipedia.org/wiki/Ruth_Teitelbaum")
                    .obtener(),

            Nombre.es("Liskov")
                    .porque("Barbara Liskov - co-developed the Liskov substitution principle. Liskov was also the winner of the Turing Prize in 2008. - https://en.wikipedia.org/wiki/Barbara_Liskov")
                    .obtener(),

            Nombre.es("Lovelace")
                    .porque("Ada Lovelace invented the first algorithm. https://en.wikipedia.org/wiki/Ada_Lovelace (thanks James Turnbull)")
                    .obtener(),

            Nombre.es("Lumiere")
                    .porque("Auguste and Louis Lumière - the first filmmakers in history - https://en.wikipedia.org/wiki/Auguste_and_Louis_Lumi%C3%A8re")
                    .obtener(),

            Nombre.es("Mahavira")
                    .porque("Mahavira - Ancient Indian mathematician during 9th century AD who discovered basic algebraic identities - https://en.wikipedia.org/wiki/Mah%C4%81v%C4%ABra_(mathematician)")
                    .obtener(),

            Nombre.es("Margulis")
                    .porque("Lynn Margulis (b. Lynn Petra Alexander) - an American evolutionary theorist and biologist, science author, educator, and popularizer, and was the primary modern proponent for the significance of symbiosis in evolution. - https://en.wikipedia.org/wiki/Lynn_Margulis")
                    .obtener(),

            Nombre.es("Matsumoto")
                    .porque("Yukihiro Matsumoto - Japanese computer scientist and software programmer best known as the chief designer of the Ruby programming language. https://en.wikipedia.org/wiki/Yukihiro_Matsumoto")
                    .obtener(),

            Nombre.es("Maxwell")
                    .porque("James Clerk Maxwell - Scottish physicist, best known for his formulation of electromagnetic theory. https://en.wikipedia.org/wiki/James_Clerk_Maxwell")
                    .obtener(),

            Nombre.es("Mayer")
                    .porque("Maria Mayer - American theoretical physicist and Nobel laureate in Physics for proposing the nuclear shell model of the atomic nucleus - https://en.wikipedia.org/wiki/Maria_Mayer")
                    .obtener(),

            Nombre.es("Mccarthy")
                    .porque("John McCarthy invented LISP: https://en.wikipedia.org/wiki/John_McCarthy_(computer_scientist)")
                    .obtener(),

            Nombre.es("Mcclintock")
                    .porque("Barbara McClintock - a distinguished American cytogeneticist, 1983 Nobel Laureate in Physiology or Medicine for discovering transposons. https://en.wikipedia.org/wiki/Barbara_McClintock")
                    .obtener(),

            Nombre.es("Mclaren")
                    .porque("Anne Laura Dorinthea McLaren - British developmental biologist whose work helped lead to human in-vitro fertilisation. https://en.wikipedia.org/wiki/Anne_McLaren")
                    .obtener(),

            Nombre.es("Mclean")
                    .porque("Malcolm McLean invented the modern shipping container: https://en.wikipedia.org/wiki/Malcom_McLean")
                    .obtener(),

            Nombre.es("Mcnulty")
                    .porque("Kay McNulty - one of the original programmers of the ENIAC. https://en.wikipedia.org/wiki/ENIAC - https://en.wikipedia.org/wiki/Kathleen_Antonelli")
                    .obtener(),

            Nombre.es("Meitner")
                    .porque("Lise Meitner - Austrian/Swedish physicist who was involved in the discovery of nuclear fission. The element meitnerium is named after her - https://en.wikipedia.org/wiki/Lise_Meitner")
                    .obtener(),

            Nombre.es("Mendel")
                    .porque("Gregor Johann Mendel - Czech scientist and founder of genetics. https://en.wikipedia.org/wiki/Gregor_Mendel")
                    .obtener(),

            Nombre.es("Mendeleev")
                    .porque("Dmitri Mendeleev - a chemist and inventor. He formulated the Periodic Law, created a farsighted version of the periodic table of elements, and used it to correct the properties of some already discovered elements and also to predict the properties of eight elements yet to be discovered. https://en.wikipedia.org/wiki/Dmitri_Mendeleev")
                    .obtener(),

            Nombre.es("Meninsky")
                    .porque("Carla Meninsky, was the game designer and programmer for Atari 2600 games Dodge 'Em and Warlords. https://en.wikipedia.org/wiki/Carla_Meninsky")
                    .obtener(),

            Nombre.es("Merkle")
                    .porque("Ralph C. Merkle - American computer scientist, known for devising Merkle's puzzles - one of the very first schemes for public-key cryptography. Also, inventor of Merkle trees and co-inventor of the Merkle-Damgård construction for building collision-resistant cryptographic hash functions and the Merkle-Hellman knapsack cryptosystem. https://en.wikipedia.org/wiki/Ralph_Merkle")
                    .obtener(),

            Nombre.es("Mestorf")
                    .porque("Johanna Mestorf - German prehistoric archaeologist and first female museum director in Germany - https://en.wikipedia.org/wiki/Johanna_Mestorf")
                    .obtener(),

            Nombre.es("Minsky")
                    .porque("Marvin Minsky - Pioneer in Artificial Intelligence, co-founder of the MIT's AI Lab, won the Turing Award in 1969. https://en.wikipedia.org/wiki/Marvin_Minsky")
                    .obtener(),

            Nombre.es("Mirzakhani")
                    .porque("Maryam Mirzakhani - an Iranian mathematician and the first woman to win the Fields Medal. https://en.wikipedia.org/wiki/Maryam_Mirzakhani")
                    .obtener(),

            Nombre.es("Montalcini")
                    .porque("Rita Levi-Montalcini - Won Nobel Prize in Physiology or Medicine jointly with colleague Stanley Cohen for the discovery of nerve growth factor (https://en.wikipedia.org/wiki/Rita_Levi-Montalcini)")
                    .obtener(),

            Nombre.es("Moore")
                    .porque("Gordon Earle Moore - American engineer, Silicon Valley founding father, author of Moore's law. https://en.wikipedia.org/wiki/Gordon_Moore")
                    .obtener(),

            Nombre.es("Morse")
                    .porque("Samuel Morse - contributed to the invention of a single-wire telegraph system based on European telegraphs and was a co-developer of the Morse code - https://en.wikipedia.org/wiki/Samuel_Morse")
                    .obtener(),

            Nombre.es("Moser")
                    .porque("May-Britt Moser - Nobel prize winner neuroscientist who contributed to the discovery of grid cells in the brain. https://en.wikipedia.org/wiki/May-Britt_Moser")
                    .obtener(),

            Nombre.es("Murdock")
                    .porque("Ian Murdock - founder of the Debian project - https://en.wikipedia.org/wiki/Ian_Murdock")
                    .obtener(),

            Nombre.es("Napier")
                    .porque("John Napier of Merchiston - Scottish landowner known as an astronomer, mathematician and physicist. Best known for his discovery of logarithms. https://en.wikipedia.org/wiki/John_Napier")
                    .obtener(),

            Nombre.es("Nash")
                    .porque("John Forbes Nash, Jr. - American mathematician who made fundamental contributions to game theory, differential geometry, and the study of partial differential equations. https://en.wikipedia.org/wiki/John_Forbes_Nash_Jr.")
                    .obtener(),

            Nombre.es("Neumann")
                    .porque("John von Neumann - todays computer architectures are based on the von Neumann architecture. https://en.wikipedia.org/wiki/Von_Neumann_architecture")
                    .obtener(),

            Nombre.es("Newton")
                    .porque("Isaac Newton invented classic mechanics and modern optics. https://en.wikipedia.org/wiki/Isaac_Newton")
                    .obtener(),

            Nombre.es("Nightingale")
                    .porque("Florence Nightingale, more prominently known as a nurse, was also the first female member of the Royal Statistical Society and a pioneer in statistical graphics https://en.wikipedia.org/wiki/Florence_Nightingale#Statistics_and_sanitary_reform")
                    .obtener(),

            Nombre.es("Nobel")
                    .porque("Alfred Nobel - a Swedish chemist, engineer, innovator, and armaments manufacturer (inventor of dynamite) - https://en.wikipedia.org/wiki/Alfred_Nobel")
                    .obtener(),

            Nombre.es("Noether")
                    .porque("Emmy Noether, German mathematician. Noether's Theorem is named after her. https://en.wikipedia.org/wiki/Emmy_Noether")
                    .obtener(),

            Nombre.es("Northcutt")
                    .porque("Poppy Northcutt. Poppy Northcutt was the first woman to work as part of NASA’s Mission Control. http://www.businessinsider.com/poppy-northcutt-helped-apollo-astronauts-2014-12?op=1")
                    .obtener(),

            Nombre.es("Noyce")
                    .porque("Robert Noyce invented silicone integrated circuits and gave Silicon Valley its name. - https://en.wikipedia.org/wiki/Robert_Noyce")
                    .obtener(),

            Nombre.es("Panini")
                    .porque("Panini - Ancient Indian linguist and grammarian from 4th century CE who worked on the world's first formal system - https://en.wikipedia.org/wiki/P%C4%81%E1%B9%87ini#Comparison_with_modern_formal_systems")
                    .obtener(),

            Nombre.es("Pare")
                    .porque("Ambroise Pare invented modern surgery. https://en.wikipedia.org/wiki/Ambroise_Par%C3%A9")
                    .obtener(),

            Nombre.es("Pascal")
                    .porque("Blaise Pascal, French mathematician, physicist, and inventor - https://en.wikipedia.org/wiki/Blaise_Pascal")
                    .obtener(),

            Nombre.es("Pasteur")
                    .porque("Louis Pasteur discovered vaccination, fermentation and pasteurization. https://en.wikipedia.org/wiki/Louis_Pasteur.")
                    .obtener(),

            Nombre.es("Payne")
                    .porque("Cecilia Payne-Gaposchkin was an astronomer and astrophysicist who, in 1925, proposed in her Ph.D. thesis an explanation for the composition of stars in terms of the relative abundances of hydrogen and helium. https://en.wikipedia.org/wiki/Cecilia_Payne-Gaposchkin")
                    .obtener(),

            Nombre.es("Perlman")
                    .porque("Radia Perlman is a software designer and network engineer and most famous for her invention of the spanning-tree protocol (STP). https://en.wikipedia.org/wiki/Radia_Perlman")
                    .obtener(),

            Nombre.es("Pike")
                    .porque("Rob Pike was a key contributor to Unix, Plan 9, the X graphic system, utf-8, and the Go programming language. https://en.wikipedia.org/wiki/Rob_Pike")
                    .obtener(),

            Nombre.es("Poincare")
                    .porque("Henri Poincaré made fundamental contributions in several fields of mathematics. https://en.wikipedia.org/wiki/Henri_Poincar%C3%A9")
                    .obtener(),

            Nombre.es("Poitras")
                    .porque("Laura Poitras is a director and producer whose work, made possible by open source crypto tools, advances the causes of truth and freedom of information by reporting disclosures by whistleblowers such as Edward Snowden. https://en.wikipedia.org/wiki/Laura_Poitras")
                    .obtener(),

            Nombre.es("Proskuriakova")
                    .porque("Tat’yana Avenirovna Proskuriakova (Russian: Татья́на Авени́ровна Проскуряко́ва) (January 23 [O.S. January 10] 1909 – August 30, 1985) was a Russian-American Mayanist scholar and archaeologist who contributed significantly to the deciphering of Maya hieroglyphs, the writing system of the pre-Columbian Maya civilization of Mesoamerica. https://en.wikipedia.org/wiki/Tatiana_Proskouriakoff")
                    .obtener(),

            Nombre.es("Ptolemy")
                    .porque("Claudius Ptolemy - a Greco-Egyptian writer of Alexandria, known as a mathematician, astronomer, geographer, astrologer, and poet of a single epigram in the Greek Anthology - https://en.wikipedia.org/wiki/Ptolemy")
                    .obtener(),

            Nombre.es("Raman")
                    .porque("C. V. Raman - Indian physicist who won the Nobel Prize in 1930 for proposing the Raman effect. - https://en.wikipedia.org/wiki/C._V._Raman")
                    .obtener(),

            Nombre.es("Ramanujan")
                    .porque("Srinivasa Ramanujan - Indian mathematician and autodidact who made extraordinary contributions to mathematical analysis, number theory, infinite series, and continued fractions. - https://en.wikipedia.org/wiki/Srinivasa_Ramanujan")
                    .obtener(),

            Nombre.es("Rhodes")
                    .porque("Ida Rhodes - American pioneer in computer programming, designed the first computer used for Social Security. https://en.wikipedia.org/wiki/Ida_Rhodes")
                    .obtener(),

            Nombre.es("Ride")
                    .porque("Sally Kristen Ride was an American physicist and astronaut. She was the first American woman in space, and the youngest American astronaut. https://en.wikipedia.org/wiki/Sally_Ride")
                    .obtener(),

            Nombre.es("Ritchie")
                    .porque("Dennis Ritchie - co-creator of UNIX and the C programming language. - https://en.wikipedia.org/wiki/Dennis_Ritchie")
                    .obtener(),

            Nombre.es("Robinson")
                    .porque("Julia Hall Bowman Robinson - American mathematician renowned for her contributions to the fields of computability theory and computational complexity theory. https://en.wikipedia.org/wiki/Julia_Robinson")
                    .obtener(),

            Nombre.es("Roentgen")
                    .porque("Wilhelm Conrad Röntgen - German physicist who was awarded the first Nobel Prize in Physics in 1901 for the discovery of X-rays (Röntgen rays). https://en.wikipedia.org/wiki/Wilhelm_R%C3%B6ntgen")
                    .obtener(),

            Nombre.es("Rosalind")
                    .porque("Rosalind Franklin - British biophysicist and X-ray crystallographer whose research was critical to the understanding of DNA - https://en.wikipedia.org/wiki/Rosalind_Franklin")
                    .obtener(),

            Nombre.es("Rubin")
                    .porque("Vera Rubin - American astronomer who pioneered work on galaxy rotation rates. https://en.wikipedia.org/wiki/Vera_Rubin")
                    .obtener(),

            Nombre.es("Saha")
                    .porque("Meghnad Saha - Indian astrophysicist best known for his development of the Saha equation, used to describe chemical and physical conditions in stars - https://en.wikipedia.org/wiki/Meghnad_Saha")
                    .obtener(),

            Nombre.es("Sammet")
                    .porque("Jean E. Sammet developed FORMAC, the first widely used computer language for symbolic manipulation of mathematical formulas. https://en.wikipedia.org/wiki/Jean_E._Sammet")
                    .obtener(),

            Nombre.es("Sanderson")
                    .porque("Mildred Sanderson - American mathematician best known for Sanderson's theorem concerning modular invariants. https://en.wikipedia.org/wiki/Mildred_Sanderson")
                    .obtener(),

            Nombre.es("Satoshi")
                    .porque("Satoshi Nakamoto is the name used by the unknown person or group of people who developed bitcoin, authored the bitcoin white paper, and created and deployed bitcoin's original reference implementation. https://en.wikipedia.org/wiki/Satoshi_Nakamoto")
                    .obtener(),

            Nombre.es("Shamir")
                    .porque("Adi Shamir - Israeli cryptographer whose numerous inventions and contributions to cryptography include the Ferge Fiat Shamir identification scheme, the Rivest Shamir Adleman (RSA) public-key cryptosystem, the Shamir's secret sharing scheme, the breaking of the Merkle-Hellman cryptosystem, the TWINKLE and TWIRL factoring devices and the discovery of differential cryptanalysis (with Eli Biham). https://en.wikipedia.org/wiki/Adi_Shamir")
                    .obtener(),

            Nombre.es("Shannon")
                    .porque("Claude Shannon - The father of information theory and founder of digital circuit design theory. (https://en.wikipedia.org/wiki/Claude_Shannon)")
                    .obtener(),

            Nombre.es("Shaw")
                    .porque("Carol Shaw - Originally an Atari employee, Carol Shaw is said to be the first female video game designer. https://en.wikipedia.org/wiki/Carol_Shaw_(video_game_designer)")
                    .obtener(),

            Nombre.es("Shirley")
                    .porque("Dame Stephanie 'Steve' Shirley - Founded a software company in 1962 employing women working from home. https://en.wikipedia.org/wiki/Steve_Shirley")
                    .obtener(),

            Nombre.es("Shockley")
                    .porque("William Shockley co-invented the transistor - https://en.wikipedia.org/wiki/William_Shockley")
                    .obtener(),

            Nombre.es("Shtern")
                    .porque("Lina Solomonovna Stern (or Shtern; Russian: Лина Соломоновна Штерн; 26 August 1878 – 7 March 1968) was a Soviet biochemist, physiologist and humanist whose medical discoveries saved thousands of lives at the fronts of World War II. She is best known for her pioneering work on blood–brain barrier, which she described as hemato-encephalic barrier in 1921. https://en.wikipedia.org/wiki/Lina_Stern")
                    .obtener(),

            Nombre.es("Sinoussi")
                    .porque("Françoise Barré-Sinoussi - French virologist and Nobel Prize Laureate in Physiology or Medicine; her work was fundamental in identifying HIV as the cause of AIDS. https://en.wikipedia.org/wiki/Fran%C3%A7oise_Barr%C3%A9-Sinoussi")
                    .obtener(),

            Nombre.es("Snyder")
                    .porque("Betty Snyder - one of the original programmers of the ENIAC. https://en.wikipedia.org/wiki/ENIAC - https://en.wikipedia.org/wiki/Betty_Holberton")
                    .obtener(),

            Nombre.es("Solomon")
                    .porque("Cynthia Solomon - Pioneer in the fields of artificial intelligence, computer science and educational computing. Known for creation of Logo, an educational programming language.  https://en.wikipedia.org/wiki/Cynthia_Solomon")
                    .obtener(),

            Nombre.es("Spence")
                    .porque("Frances Spence - one of the original programmers of the ENIAC. https://en.wikipedia.org/wiki/ENIAC - https://en.wikipedia.org/wiki/Frances_Spence")
                    .obtener(),

            Nombre.es("Stallman")
                    .porque("Richard Matthew Stallman - the founder of the Free Software movement, the GNU project, the Free Software Foundation, and the League for Programming Freedom. He also invented the concept of copyleft to protect the ideals of this movement, and enshrined this concept in the widely-used GPL (General Public License) for software. https://en.wikiquote.org/wiki/Richard_Stallman")
                    .obtener(),

            Nombre.es("Stonebraker")
                    .porque("Michael Stonebraker is a database research pioneer and architect of Ingres, Postgres, VoltDB and SciDB. Winner of 2014 ACM Turing Award. https://en.wikipedia.org/wiki/Michael_Stonebraker")
                    .obtener(),

            Nombre.es("Sutherland")
                    .porque("Ivan Edward Sutherland - American computer scientist and Internet pioneer, widely regarded as the father of computer graphics. https://en.wikipedia.org/wiki/Ivan_Sutherland")
                    .obtener(),

            Nombre.es("Swanson")
                    .porque("Janese Swanson (with others) developed the first of the Carmen Sandiego games. She went on to found Girl Tech. https://en.wikipedia.org/wiki/Janese_Swanson")
                    .obtener(),

            Nombre.es("Swartz")
                    .porque("Aaron Swartz was influential in creating RSS, Markdown, Creative Commons, Reddit, and much of the internet as we know it today. He was devoted to freedom of information on the web. https://en.wikiquote.org/wiki/Aaron_Swartz")
                    .obtener(),

            Nombre.es("Swirles")
                    .porque("Bertha Swirles was a theoretical physicist who made a number of contributions to early quantum theory. https://en.wikipedia.org/wiki/Bertha_Swirles")
                    .obtener(),

            Nombre.es("Taussig")
                    .porque("Helen Brooke Taussig - American cardiologist and founder of the field of paediatric cardiology. https://en.wikipedia.org/wiki/Helen_B._Taussig")
                    .obtener(),

            Nombre.es("Tereshkova")
                    .porque("Valentina Tereshkova is a Russian engineer, cosmonaut and politician. She was the first woman to fly to space in 1963. In 2013, at the age of 76, she offered to go on a one-way mission to Mars. https://en.wikipedia.org/wiki/Valentina_Tereshkova")
                    .obtener(),

            Nombre.es("Tesla")
                    .porque("Nikola Tesla invented the AC electric system and every gadget ever used by a James Bond villain. https://en.wikipedia.org/wiki/Nikola_Tesla")
                    .obtener(),

            Nombre.es("Tharp")
                    .porque("Marie Tharp - American geologist and oceanic cartographer who co-created the first scientific map of the Atlantic Ocean floor. Her work led to the acceptance of the theories of plate tectonics and continental drift. https://en.wikipedia.org/wiki/Marie_Tharp")
                    .obtener(),

            Nombre.es("Thompson")
                    .porque("Ken Thompson - co-creator of UNIX and the C programming language - https://en.wikipedia.org/wiki/Ken_Thompson")
                    .obtener(),

            Nombre.es("Torvalds")
                    .porque("Linus Torvalds invented Linux and Git. https://en.wikipedia.org/wiki/Linus_Torvalds")
                    .obtener(),

            Nombre.es("Tu")
                    .porque("Youyou Tu - Chinese pharmaceutical chemist and educator known for discovering artemisinin and dihydroartemisinin, used to treat malaria, which has saved millions of lives. Joint winner of the 2015 Nobel Prize in Physiology or Medicine. https://en.wikipedia.org/wiki/Tu_Youyou")
                    .obtener(),

            Nombre.es("Turing")
                    .porque("Alan Turing was a founding father of computer science. https://en.wikipedia.org/wiki/Alan_Turing.")
                    .obtener(),

            Nombre.es("Varahamihira")
                    .porque("Varahamihira - Ancient Indian mathematician who discovered trigonometric formulae during 505-587 CE - https://en.wikipedia.org/wiki/Var%C4%81hamihira#Contributions")
                    .obtener(),

            Nombre.es("Vaughan")
                    .porque("Dorothy Vaughan was a NASA mathematician and computer programmer on the SCOUT launch vehicle program that put America's first satellites into space - https://en.wikipedia.org/wiki/Dorothy_Vaughan")
                    .obtener(),

            Nombre.es("Villani")
                    .porque("Cédric Villani - French mathematician, won Fields Medal, Fermat Prize and Poincaré Price for his work in differential geometry and statistical mechanics. https://en.wikipedia.org/wiki/C%C3%A9dric_Villani")
                    .obtener(),

            Nombre.es("Visvesvaraya")
                    .porque("Sir Mokshagundam Visvesvaraya - is a notable Indian engineer.  He is a recipient of the Indian Republic's highest honour, the Bharat Ratna, in 1955. On his birthday, 15 September is celebrated as Engineer's Day in India in his memory - https://en.wikipedia.org/wiki/Visvesvaraya")
                    .obtener(),

            Nombre.es("Volhard")
                    .porque("Christiane Nüsslein-Volhard - German biologist, won Nobel Prize in Physiology or Medicine in 1995 for research on the genetic control of embryonic development. https://en.wikipedia.org/wiki/Christiane_N%C3%BCsslein-Volhard")
                    .obtener(),

            Nombre.es("Wescoff")
                    .porque("Marlyn Wescoff - one of the original programmers of the ENIAC. https://en.wikipedia.org/wiki/ENIAC - https://en.wikipedia.org/wiki/Marlyn_Meltzer")
                    .obtener(),

            Nombre.es("Wilbur")
                    .porque("Sylvia B. Wilbur - British computer scientist who helped develop the ARPANET, was one of the first to exchange email in the UK and a leading researcher in computer-supported collaborative work. https://en.wikipedia.org/wiki/Sylvia_Wilbur")
                    .obtener(),

            Nombre.es("Wiles")
                    .porque("Andrew Wiles - Notable British mathematician who proved the enigmatic Fermat's Last Theorem - https://en.wikipedia.org/wiki/Andrew_Wiles")
                    .obtener(),

            Nombre.es("Williams")
                    .porque("Roberta Williams, did pioneering work in graphical adventure games for personal computers, particularly the King's Quest series. https://en.wikipedia.org/wiki/Roberta_Williams")
                    .obtener(),

            Nombre.es("Williamson")
                    .porque("Malcolm John Williamson - British mathematician and cryptographer employed by the GCHQ. Developed in 1974 what is now known as Diffie-Hellman key exchange (Diffie and Hellman first published the scheme in 1976). https://en.wikipedia.org/wiki/Malcolm_J._Williamson")
                    .obtener(),

            Nombre.es("Wilson")
                    .porque("Sophie Wilson designed the first Acorn Micro-Computer and the instruction set for ARM processors. https://en.wikipedia.org/wiki/Sophie_Wilson")
                    .obtener(),

            Nombre.es("Wing")
                    .porque("Jeannette Wing - co-developed the Liskov substitution principle. - https://en.wikipedia.org/wiki/Jeannette_Wing")
                    .obtener(),

            Nombre.es("Wozniak")
                    .porque("Steve Wozniak invented the Apple I and Apple II. https://en.wikipedia.org/wiki/Steve_Wozniak")
                    .obtener(),

            Nombre.es("Wright")
                    .porque("The Wright brothers, Orville and Wilbur - credited with inventing and building the world's first successful airplane and making the first controlled, powered and sustained heavier-than-air human flight - https://en.wikipedia.org/wiki/Wright_brothers")
                    .obtener(),

            Nombre.es("Wu")
                    .porque("Chien-Shiung Wu - Chinese-American experimental physicist who made significant contributions to nuclear physics. https://en.wikipedia.org/wiki/Chien-Shiung_Wu")
                    .obtener(),

            Nombre.es("Yalow")
                    .porque("Rosalyn Sussman Yalow - Rosalyn Sussman Yalow was an American medical physicist, and a co-winner of the 1977 Nobel Prize in Physiology or Medicine for development of the radioimmunoassay technique. https://en.wikipedia.org/wiki/Rosalyn_Sussman_Yalow")
                    .obtener(),

            Nombre.es("Yonath")
                    .porque("Ada Yonath - an Israeli crystallographer, the first woman from the Middle East to win a Nobel prize in the sciences. https://en.wikipedia.org/wiki/Ada_Yonath")
                    .obtener(),

            Nombre.es("Zhukovsky")
                    .porque("Nikolay Yegorovich Zhukovsky (Russian: Никола́й Его́рович Жуко́вский, January 17 1847 – March 17, 1921) was a Russian scientist, mathematician and engineer, and a founding father of modern aero- and hydrodynamics. Whereas contemporary scientists scoffed at the idea of human flight, Zhukovsky was the first to undertake the study of airflow. He is often called the Father of Russian Aviation. https://en.wikipedia.org/wiki/Nikolay_Yegorovich_Zhukovsky")
                    .obtener(),

    };


    public static List<Nombre> listar() {

        List<Nombre> todos = new LinkedList<>();

        Collections.addAll(todos, NOMBRES);

        return todos;
    }
}
