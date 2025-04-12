# Informace o projektu #
Tento repozitář sloužil k osvojení si DevOps postupů. Nejdříve byl napsán __Dockerfile__ pomocí něhož se vytvořil Docker image.

V __.gitlab-ci.yml__ jsem vypracoval skript, který vybudoval projekt pomocí _maven_ a identifikoval artefakty (binárky), které byly pak důležité k sestavení kontejneru, který byl pak uložen do Gitlab container registry. 

Pracovali jsme také s virtuálkami běžícími na školním cloudu. K manipulaci s nimi jsme používali __Openstack__. Byla vytvořena jedna řídící virtuálka, na které jsem vytvořil __webservers__ soubor, který vlastnil IP adresy dalších dvou řízených virtuálek. Poté byl vytvořen __Playbook__ soubor, jehož ukázka je i v tomto repozitáři. Tento playbook připravil ostatní dvě virtuálky a stáhl na ně právě tuto aplikaci z docker registry. Po předání SSH klíče řídící virtuálky do _authorized_keys_ druhé virtuálky -- čímž ta řídící virtuálka získala přístup -- už bylo možné zavolat "ansible-playbook -i hosts webserver.yml".

Tímto se na řízených virtuálkách spustil Docker kontejner s touto aplikací a zadáním IP adresy řízené virtuálky s portem do prohlížeče šlo aplikaci standardně využívat. 
