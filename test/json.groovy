import groovy.json.JsonOutput
@Grab(group = 'org.json', module = 'json', version = '20140107')
import org.json.JSONObject;



jsonObject = new JSONObject(new File("../posGridView/src/main/res/raw/catalog.json").text)
jsonArray = jsonObject.names()

entrees = jsonObject.get("Entrees")
for (int i = 0; i < entrees.length(); i++) {
    JSONObject o = entrees.get(i)
    println o.names()
}
