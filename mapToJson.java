                                        package mapToJson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

public class mapToJson {
	//json字符串-简单对象型
	private static final String  JSON_OBJ_STR = "{\"studentName\":\"lily\",\"studentAge\":12}";
	//json字符串-数组类型
	private static final String  JSON_ARRAY_STR = "[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]";
	//复杂格式json字符串
	private static final String  COMPLEX_JSON_STR = "{\"teacherName\":\"crystall\",\"teacherAge\":27,\"course\":{\"courseName\":\"english\",\"code\":1270},\"students\":[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]}";

    /**
     * json字符串-简单对象型与JSONObject之间的转换
     */
	@Test
    public void testJSONStrToJSONObject(){
		
        JSONObject jsonObject = JSON.parseObject(JSON_OBJ_STR);
        //JSONObject jsonObject1 = JSONObject.parseObject(JSON_OBJ_STR); //因为JSONObject继承了JSON，所以这样也是可以的
 
        System.out.println(jsonObject.getString("studentName")+":"+jsonObject.getInteger("studentAge"));
        String jsonStr = JSONObject.toJSONString(jsonObject);
        System.out.println(jsonStr);
    }
    /**
     * json字符串-数组类型与JSONArray之间的转换
     */
	@Test
    public  void testJSONStrToJSONArray(){
 
        JSONArray jsonArray = JSON.parseArray(JSON_ARRAY_STR);
        //JSONArray jsonArray1 = JSONArray.parseArray(JSON_ARRAY_STR);//因为JSONArray继承了JSON，所以这样也是可以的
 
        //遍历方式1
        int size = jsonArray.size();
        for (int i = 0; i < size; i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            System.out.println(jsonObject.getString("studentName")+":"+jsonObject.getInteger("studentAge"));
        }
        //遍历方式2
        for (Object obj : jsonArray) {
            JSONObject jsonObject = (JSONObject) obj;
            System.out.println(jsonObject.getString("studentName")+":"+jsonObject.getInteger("studentAge"));
        }
    }

    /**
     * 复杂json格式字符串与JSONObject之间的转换
     */
	@Test
    public void testComplexJSONStrToJSONObject(){
 
        JSONObject jsonObject = JSON.parseObject(COMPLEX_JSON_STR);
        //JSONObject jsonObject1 = JSONObject.parseObject(COMPLEX_JSON_STR);//因为JSONObject继承了JSON，所以这样也是可以的
        
        String teacherName = jsonObject.getString("teacherName");
        Integer teacherAge = jsonObject.getInteger("teacherAge");
        JSONObject course = jsonObject.getJSONObject("course");
        JSONArray students = jsonObject.getJSONArray("students");
        
        Student s = new Student();
        s.setStudentName("王五");
        s.setStudentAge(12);
        JSONObject obj = (JSONObject) JSON.toJSON(s);
        
        //System.out.println(obj.toJSONString());
        Map smap = JSONObject.parseObject(obj.toJSONString(), Map.class);
        //System.out.println(smap);
        
        
        String str=JSONObject.toJSONString((Object)s);
        Map smap2 = JSONObject.parseObject(str, Map.class);
        //System.out.println(smap2);
        
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("studentName", "张三");
        map.put("studentAge", 20);
        
        String strmap=JSONObject.toJSONString((Object)map);
        Student stu = JSONObject.parseObject(strmap, Student.class);
        System.out.println(stu);
        
    }
	
	   /**
     * json字符串-简单对象与JavaBean_obj之间的转换
     */
	@Test
    public  void testJSONStrToJavaBeanObj(){
 
        Student student = JSON.parseObject(JSON_OBJ_STR, Student.class);
        //Student student1 = JSONObject.parseObject(JSON_OBJ_STR, new TypeReference<Student>() {});//因为JSONObject继承了JSON，所以这样也是可以的
 
        System.out.println(student.getStudentName()+":"+student.getStudentAge());
 
    }

	/**
     * json字符串-数组类型与JavaBean_List之间的转换
     */
	@Test
    public void testJSONStrToJavaBeanList(){
        
        ArrayList<Student> students = JSON.parseObject(JSON_ARRAY_STR, new TypeReference<ArrayList<Student>>() {});
        //ArrayList<Student> students1 = JSONArray.parseObject(JSON_ARRAY_STR, new TypeReference<ArrayList<Student>>() {});//因为JSONArray继承了JSON，所以这样也是可以的
        
        for (Student student : students) {
            System.out.println(student.getStudentName()+":"+student.getStudentAge());
        }
    }

	@Test
	public void html(){
		String str = "<div style=\"\"><font face=\"helvetica, arial, verdana, sans-serif\">"
				+ "正在热映的《碟中谍5》相信很多小伙伴们都已经刷过了吧~手扒飞机的阿汤哥帅得让人忘记了他的年龄，忽略了他的身高。不过看完这部片子世纪君思考起了一个有深度的问题，英文片名：Mission Impossible，是怎么从“不可能完成的任务”变成“碟中谍”的呢？"
				+ "</font></div><div style=\"\"><font face=\"helvetica, arial, verdana, sans-serif\"><br></font></div><div style=\"\"><font face=\"helvetica, arial, verdana, sans-serif\">所以，今天就来一起探讨一下关于英文电影那些看似“文不对题”细想让人拍案叫绝的中文译名吧。</font></div><div style=\"\"><font face=\"helvetica, arial, verdana, sans-serif\"><br></font></div><div style=\"\">"
				+ "<font face=\"helvetica, arial, verdana, sans-serif\">《碟中谍》</font></div><div style=\"\"><font face=\"helvetica, arial, verdana, sans-serif\"><br></font></div><div style=\"\">"
				+ "<font face=\"helvetica, arial, verdana, sans-serif\">Mission Impossible</font></div><div style=\"\"><img src=\"http://221.181.100.37:8082/upload-image/images/20180829/20180829152809971.jpg\" width=\"640\" height=\"977\"><br></div><div style=\"\">"
				+ "<font face=\"helvetica, arial, verdana, sans-serif\">"
				+ "Mission Impossible的中文译名还要从19年前的第一部说起。1996年《碟1》上映，这部动作大片讲的是布拉格的美国使馆工作人员偷走了CIA的半份间谍名单，而阿汤哥扮演的特工亨特的任务就是去找回名单。"
				+ "这份名单藏在一张磁碟中，所以这下就清晰明了了吧，磁碟中的间谍名单——《碟中谍》。"
				+ "</font></div><div style=\"\"><font face=\"helvetica, arial, verdana, sans-serif\"><br></font></div>"
				+ "<div style=\"\"><font face=\"helvetica, arial, verdana, sans-serif\">Plot: An American agent, under false suspicion of disloyalty, must discover and expose the real spy without the help of his organization.</font></div><div style=\"\"><font face=\"helvetica, arial, verdana, sans-serif\"><br></font></div><div style=\"\">"
				+ "<font face=\"helvetica, arial, verdana, sans-serif\">《盗梦空间》</font></div><div style=\"\"><font face=\"helvetica, arial, verdana, sans-serif\"><br></font></div><div style=\"\"><font face=\"helvetica, arial, verdana, sans-serif\">Inception</font></div><div style=\"\">"
				+ "<img src=\"http://221.181.100.37:8082/upload-image/images/20180829/20180829152819829.jpg\" width=\"640\" height=\"948\"><br></div><div style=\"\">"
				+ "<font face=\"helvetica, arial, verdana, sans-serif\">Inception的原意是“起初”、“开端”，the establishment or starting point of an institution or activity。"
				+ "原本的片名暗含小李子带着一群人为了完成一项任务，“一个想法慢慢开始发展、扩散”。内地上映时的名字基本上已经跟英文片名无关了，而是根据影片内容重新起了一个，既概括了故事情节，也制造了一种悬疑的效果。"
				+ "</font></div><div style=\"\"><font face=\"helvetica, arial, verdana, sans-serif\"><br></font></div><div style=\"\"><font face=\"helvetica, arial, verdana, sans-serif\">"
				+ "Plot: A thief who steals corporate secrets through use of dream-sharing technology is given the inverse task of planting an idea into the mind of a CEO."
				+ "</font></div><div style=\"\"><font face=\"helvetica, arial, verdana, sans-serif\"><br></font></div><div style=\"\"><font face=\"helvetica, arial, verdana, sans-serif\">《"
				+ "魂断蓝桥》</font></div><div style=\"\"><font face=\"helvetica, arial, verdana, sans-serif\"><br></font></div><div style=\"\">"
				+ "<font face=\"helvetica, arial, verdana, sans-serif\">Waterloo Bridge</font></div><div style=\"\">"
				+ "<img src=\"http://221.181.100.37:8082/upload-image/images/20180829/20180829152828514.jpg\" width=\"640\" height=\"836\">"
				+ "<br></div><div style=\"\"><font face=\"helvetica, arial, verdana, sans-serif\">这部荡气回肠的经典爱情片的英文名直译过来是“滑铁卢桥”，也就是故事发生的地点。"
				+ "“魂断”两个字让人一看就知道这是一个爱情悲剧。那么“滑铁卢桥”是如何演变为“蓝桥”的呢？"
				+ "《庄子》中有一个哀怨凄婉的爱情故事，一个痴情汉与心爱的姑娘相约桥上，女子迟迟未赴约，信守诺言坚持等待的男子最后溺亡。"
				+ "这座桥被后人称为“蓝桥”。所以这个凄美的译名还极具中国特色。而巧合的是，blue在英文中还有“忧郁”的意思。“魂断蓝桥”，真是绝了。"
				+ "</font></div><div style=\"\"><font face=\"helvetica, arial, verdana, sans-serif\"><br></font></div>"
				+ "<div style=\"\"><font face=\"helvetica, arial, verdana, sans-serif\">"
				+ "Plot: During WWI, believing her fiancé to be dead, a young ballerina loses her job and is forced to turn to prostitution."
				+ "</font></div><div style=\"\"><font face=\"helvetica, arial, verdana, sans-serif\"><br></font></div><div style=\"\">"
				+ "<font face=\"helvetica, arial, verdana, sans-serif\">《廊桥遗梦》</font></div><div style=\"\">"
				+ "<font face=\"helvetica, arial, verdana, sans-serif\"><br></font></div><div style=\"\">"
				+ "<font face=\"helvetica, arial, verdana, sans-serif\">The Bridges of Madison County</font></div>"
				+ "<div style=\"\"><font face=\"helvetica, arial, verdana, sans-serif\">"
				+ "<img src=\"http://221.181.100.37:8082/upload-image/images/20180829/20180829152836869.jpg\" width=\"640\" height=\"960\"><br></font></div><div style=\"\">"
				+ "<font face=\"helvetica, arial, verdana, sans-serif\">和《魂断蓝桥》类似，"
				+ "原本的英文名只是一个简单的地点，而中文译名中“遗梦”两字让观众知道这是一个充满遗憾的故事。"
				+ "影片讲述了一位家庭主妇在家人外出的四天里遇到了一位摄影记者，在经历了短暂的浪漫缠绵之后，因对家庭的不舍不得不痛苦分开，"
				+ "但是对彼此的爱恋却萦绕了他们整个后半生。</font></div><div style=\"\">"
				+ "<font face=\"helvetica, arial, verdana, sans-serif\"><br></font></div><div style=\"\">"
				+ "<font face=\"helvetica, arial, verdana, sans-serif\">"
				+ "Plot: Photographer Robert Kincaid wanders into the life of housewife Francesca Johnson, for four days in the 1960s.</font></div><div style=\"\">"
				+ "<font face=\"helvetica, arial, verdana, sans-serif\"><br></font></div><div style=\"\">"
				+ "<font face=\"helvetica, arial, verdana, sans-serif\">《乱世佳人》</font></div><div style=\"\">"
				+ "<font face=\"helvetica, arial, verdana, sans-serif\"><br></font></div><div style=\"\">"
				+ "<font face=\"helvetica, arial, verdana, sans-serif\">Gone With the Wind</font></div><div style=\"\">"
				+ "<img src=\"http://221.181.100.37:8082/upload-image/images/20180829/20180829152845485.jpg\" width=\"640\" height=\"802\"><br></div>"
				+ "<div style=\"\"><font face=\"helvetica, arial, verdana, sans-serif\">原著长篇小说的中文译名是《飘》，"
				+ "一个简单的汉字，就将女主角郝思嘉流离漂泊的一生表现得淋漓尽致。电影的译名则更直白，乱世点出了故事发生的背景，佳人就是美丽、"
				+ "聪明而倔强的郝思嘉。</font></div><div style=\"\"><font face=\"helvetica, arial, verdana, sans-serif\"><br></font></div><div style=\"\">"
				+ "<font face=\"helvetica, arial, verdana, sans-serif\">"
				+ "Plot: A manipulative Southern belle carries on a turbulent affair with a blockade runner during the American Civil War.</font></div><div style=\"\">"
				+ "<font face=\"helvetica, arial, verdana, sans-serif\"><br></font></div><div style=\"\">"
				+ "<font face=\"helvetica, arial, verdana, sans-serif\">《人鬼情未了》</font></div><div style=\"\">"
				+ "<font face=\"helvetica, arial, verdana, sans-serif\"><br></font></div><div style=\"\">"
				+ "<font face=\"helvetica, arial, verdana, sans-serif\">Ghost</font></div><div style=\"\">"
				+ "<img src=\"http://221.181.100.37:8082/upload-image/images/20180829/20180829152852586.jpg\" width=\"466\" height=\"252\"><br></div>"
				+ "<div style=\"\"><font face=\"helvetica, arial, verdana, sans-serif\">"
				+ "这个片名如果直译的话实在是有点儿恐怖。而中文译名“人鬼”暗示了主角阴阳两隔的命运，“情未了”则是对故事情节的完美总结。"
				+ "</font></div><div style=\"\"><font face=\"helvetica, arial, verdana, sans-serif\"><br></font></div>"
				+ "<div style=\"\"><font face=\"helvetica, arial, verdana, sans-serif\">"
				+ "Plot: After an accident leaves a young man dead, his spirit stays behind to warn his lover of impending danger,"
				+ " with the help of a reluctant psychic.</font></div><div style=\"\"><font face=\"helvetica, arial, verdana, sans-serif\">"
				+ "<br></font></div><div style=\"\"><font face=\"helvetica, arial, verdana, sans-serif\">《彗星美人》"
				+ "</font></div><div style=\"\"><font face=\"helvetica, arial, verdana, sans-serif\"><br></font></div><div style=\"\">"
				+ "<font face=\"helvetica, arial, verdana, sans-serif\">All About Eve</font></div><div style=\"\">"
				+ "<img src=\"http://221.181.100.37:8082/upload-image/images/20180829/20180829152901160.jpg\" width=\"640\" height=\"809\">"
				+ "<br></div><div style=\"\"><font face=\"helvetica, arial, verdana, sans-serif\">"
				+ "这部曾13次提名奥斯卡的经典影片，片名直译是“关于夏娃的一切”。不过这并不是一部关于夏娃、亚当和苹果的故事，而是讲述一位有野心、"
				+ "有心计的女性如何登上名利之巅。“彗星美人”体现了演艺圈一代新人换旧人的残酷现实。</font></div><div style=\"\">"
				+ "<font face=\"helvetica, arial, verdana, sans-serif\"><br></font></div><div style=\"\">"
				+ "<font face=\"helvetica, arial, verdana, sans-serif\">"
				+ "Plot: An ingenue insinuates herself in to the company of an established but aging stage actress and "
				+ "her circle of theater friends.</font></div><div style=\"\">"
				+ "<font face=\"helvetica, arial, verdana, sans-serif\"><br></font></div><div style=\"\">"
				+ "<font face=\"helvetica, arial, verdana, sans-serif\">《飞屋环游记》</font></div>"
				+ "<div style=\"\"><font face=\"helvetica, arial, verdana, sans-serif\"><br></font></div><div style=\"\">"
				+ "<font face=\"helvetica, arial, verdana, sans-serif\">Up</font></div>"
				+ "<div style=\"\"><img src=\"http://221.181.100.37:8082/upload-image/images/20180829/20180829152908380.jpg\" width=\"640\" height=\"497\"><br></div><div style=\"\">"
				+ "<font face=\"helvetica, arial, verdana, sans-serif\">"
				+ "皮克斯的许多动画片名字都非常简洁，除了Up，还有Cars，Wall.E等。如果直接翻译成“向上”、“汽车”、“瓦力”未免太过直白。针对动画片受众年龄比较低的特点，也就诞生了“飞屋环游记”这样更具体形象的译名。"
				+ "</font></div><div style=\"\">"
				+ "<font face=\"helvetica, arial, verdana, sans-serif\"><br></font></div><"
				+ "div style=\"\"><font face=\"helvetica, arial, verdana, sans-serif\">"
				+ "Plot: Seventy-eight year old Carl Fredricksen travels to Paradise Falls in his home equipped with balloons, "
				+ "inadvertently taking a young stowaway.</font></div><div style=\"\">"
				+ "<font face=\"helvetica, arial, verdana, sans-serif\"><br></font></div>"
				+ "<div style=\"color: rgb(0, 0, 0); font-family: helvetica, arial, verdana, sans-serif; font-size: 16px;\"><br></div>";
				
		
		System.out.println(HTMLUtils.cleanHtml(str));
				
	}
}
正在热映的《碟中谍5》相信很多小伙伴们都已经刷过了吧~手扒飞机的阿汤哥帅得让人忘记了他的年龄，忽略了他的身高。
不过看完这部片子世纪君思考起了一个有深度的问题，英文片名：Mission Impossible，是怎么从“不可能完成的任务”变成“碟中谍”的呢？\n
所以，今天就来一起探讨一下关于英文电影那些看似“文不对题”细想让人拍案叫绝的中文译名吧。
\n《碟中谍》\n
Mission Impossible
¡¿¡src="http://221.181.100.37:8082/upload-image/images/20180829/20180829152809971.jpg",width="640",height="977"¡¿¡\n
Mission Impossible的中文译名还要从19年前的第一部说起。
1996年《碟1》上映，这部动作大片讲的是布拉格的美国使馆工作人员偷走了CIA的半份间谍名单，而阿汤哥扮演的特工亨特的任务就是去找回名单。
这份名单藏在一张磁碟中，所以这下就清晰明了了吧，磁碟中的间谍名单——《碟中谍》。\n
Plot: An American agent, under false suspicion of disloyalty, must discover and expose the real spy without the help of his organization.

\n《盗梦空间》\n
Inception
¡¿¡src="http://221.181.100.37:8082/upload-image/images/20180829/20180829152819829.jpg",width="640",height="948"¡¿¡\n
Inception的原意是“起初”、“开端”，the establishment or starting point of an institution or activity。
原本的片名暗含小李子带着一群人为了完成一项任务，“一个想法慢慢开始发展、扩散”。
内地上映时的名字基本上已经跟英文片名无关了，而是根据影片内容重新起了一个，既概括了故事情节，也制造了一种悬疑的效果。\n
Plot: A thief who steals corporate secrets through use of dream-sharing technology is given the inverse task of planting an idea into the mind of a CEO.

\n《魂断蓝桥》\n
Waterloo Bridge
¡¿¡src="http://221.181.100.37:8082/upload-image/images/20180829/20180829152828514.jpg",width="640",height="836"¡¿¡\n
这部荡气回肠的经典爱情片的英文名直译过来是“滑铁卢桥”，也就是故事发生的地点。“魂断”两个字让人一看就知道这是一个爱情悲剧。那么“滑铁卢桥”是如何演变为“蓝桥”的呢？
《庄子》中有一个哀怨凄婉的爱情故事，一个痴情汉与心爱的姑娘相约桥上，女子迟迟未赴约，信守诺言坚持等待的男子最后溺亡。这座桥被后人称为“蓝桥”。
所以这个凄美的译名还极具中国特色。而巧合的是，blue在英文中还有“忧郁”的意思。“魂断蓝桥”，真是绝了。\n
Plot: During WWI, believing her fiancé to be dead, a young ballerina loses her job and is forced to turn to prostitution.

\n《廊桥遗梦》\n
The Bridges of Madison County
¡¿¡src="http://221.181.100.37:8082/upload-image/images/20180829/20180829152836869.jpg",width="640",height="960"¡¿¡\n
和《魂断蓝桥》类似，原本的英文名只是一个简单的地点，而中文译名中“遗梦”两字让观众知道这是一个充满遗憾的故事。
影片讲述了一位家庭主妇在家人外出的四天里遇到了一位摄影记者，在经历了短暂的浪漫缠绵之后，因对家庭的不舍不得不痛苦分开，但是对彼此的爱恋却萦绕了他们整个后半生。\n
Plot: Photographer Robert Kincaid wanders into the life of housewife Francesca Johnson, for four days in the 1960s.

\n《乱世佳人》\n
Gone With the Wind
¡¿¡src="http://221.181.100.37:8082/upload-image/images/20180829/20180829152845485.jpg",width="640",height="802"¡¿¡\n
原著长篇小说的中文译名是《飘》，一个简单的汉字，就将女主角郝思嘉流离漂泊的一生表现得淋漓尽致。
电影的译名则更直白，乱世点出了故事发生的背景，佳人就是美丽、聪明而倔强的郝思嘉。\n
Plot: A manipulative Southern belle carries on a turbulent affair with a blockade runner during the American Civil War.

\n《人鬼情未了》\n
Ghost
¡¿¡src="http://221.181.100.37:8082/upload-image/images/20180829/20180829152852586.jpg",width="466",height="252"¡¿¡\n
这个片名如果直译的话实在是有点儿恐怖。而中文译名“人鬼”暗示了主角阴阳两隔的命运，“情未了”则是对故事情节的完美总结。\n
Plot: After an accident leaves a young man dead, his spirit stays behind to warn his lover of impending danger, with the help of a reluctant psychic.

\n《彗星美人》\n
All About Eve
¡¿¡src="http://221.181.100.37:8082/upload-image/images/20180829/20180829152901160.jpg",width="640",height="809"¡¿¡\n
这部曾13次提名奥斯卡的经典影片，片名直译是“关于夏娃的一切”。不过这并不是一部关于夏娃、亚当和苹果的故事，而是讲述一位有野心、有心计的女性如何登上名利之巅。
“彗星美人”体现了演艺圈一代新人换旧人的残酷现实。\n
Plot: An ingenue insinuates herself in to the company of an established but aging stage actress and her circle of theater friends.

\n《飞屋环游记》\n
Up
¡¿¡src="http://221.181.100.37:8082/upload-image/images/20180829/20180829152908380.jpg",width="640",height="497"¡¿¡\n
皮克斯的许多动画片名字都非常简洁，除了Up，还有Cars，Wall.E等。如果直接翻译成“向上”、“汽车”、“瓦力”未免太过直白。
针对动画片受众年龄比较低的特点，也就诞生了“飞屋环游记”这样更具体形象的译名。\n
Plot: Seventy-eight year old Carl Fredricksen travels to Paradise Falls in his home equipped with balloons, inadvertently taking a young stowaway.
 

{"miaoshu":["正在热映的《碟中谍5》相信很多小伙伴们都已经刷过了吧~手扒飞机的阿汤哥帅得让人忘记了他的年龄，忽略了他的身高。不过看完这部片子世纪君思考起了一个有深度的问题，英文片名：Mission Impossible，是怎么从“不可能完成的任务”变成“碟中谍”的呢？\\n所以，今天就来一起探讨一下关于英文电影那些看似“文不对题”细想让人拍案叫绝的中文译名吧。\\n《碟中谍》\\nMission Impossible",
"\\nMission Impossible的中文译名还要从19年前的第一部说起。1996年《碟1》上映，这部动作大片讲的是布拉格的美国使馆工作人员偷走了CIA的半份间谍名单，而阿汤哥扮演的特工亨特的任务就是去找回名单。这份名单藏在一张磁碟中，所以这下就清晰明了了吧，磁碟中的间谍名单——《碟中谍》。\\nPlot: An American agent, under false suspicion of disloyalty, must discover and expose the real spy without the help of his organization.\\n《盗梦空间》\\nInception",
"\\nInception的原意是“起初”、“开端”，the establishment or starting point of an institution or activity。原本的片名暗含小李子带着一群人为了完成一项任务，“一个想法慢慢开始发展、扩散”。内地上映时的名字基本上已经跟英文片名无关了，而是根据影片内容重新起了一个，既概括了故事情节，也制造了一种悬疑的效果。\\nPlot: A thief who steals corporate secrets through use of dream-sharing technology is given the inverse task of planting an idea into the mind of a CEO.\\n《魂断蓝桥》\\nWaterloo Bridge",
"\\n这部荡气回肠的经典爱情片的英文名直译过来是“滑铁卢桥”，也就是故事发生的地点。“魂断”两个字让人一看就知道这是一个爱情悲剧。那么“滑铁卢桥”是如何演变为“蓝桥”的呢？《庄子》中有一个哀怨凄婉的爱情故事，一个痴情汉与心爱的姑娘相约桥上，女子迟迟未赴约，信守诺言坚持等待的男子最后溺亡。这座桥被后人称为“蓝桥”。所以这个凄美的译名还极具中国特色。而巧合的是，blue在英文中还有“忧郁”的意思。“魂断蓝桥”，真是绝了。\\nPlot: During WWI, believing her fiancé to be dead, a young ballerina loses her job and is forced to turn to prostitution.\\n《廊桥遗梦》\\nThe Bridges of Madison County",
"\\n和《魂断蓝桥》类似，原本的英文名只是一个简单的地点，而中文译名中“遗梦”两字让观众知道这是一个充满遗憾的故事。影片讲述了一位家庭主妇在家人外出的四天里遇到了一位摄影记者，在经历了短暂的浪漫缠绵之后，因对家庭的不舍不得不痛苦分开，但是对彼此的爱恋却萦绕了他们整个后半生。\\nPlot: Photographer Robert Kincaid wanders into the life of housewife Francesca Johnson, for four days in the 1960s.\\n《乱世佳人》\\nGone With the Wind",
"\\n原著长篇小说的中文译名是《飘》，一个简单的汉字，就将女主角郝思嘉流离漂泊的一生表现得淋漓尽致。电影的译名则更直白，乱世点出了故事发生的背景，佳人就是美丽、聪明而倔强的郝思嘉。\\nPlot: A manipulative Southern belle carries on a turbulent affair with a blockade runner during the American Civil War.\\n《人鬼情未了》\\nGhost",
"\\n这个片名如果直译的话实在是有点儿恐怖。而中文译名“人鬼”暗示了主角阴阳两隔的命运，“情未了”则是对故事情节的完美总结。\\nPlot: After an accident leaves a young man dead, his spirit stays behind to warn his lover of impending danger, with the help of a reluctant psychic.\\n《彗星美人》\\nAll About Eve","\\n这部曾13次提名奥斯卡的经典影片，片名直译是“关于夏娃的一切”。不过这并不是一部关于夏娃、亚当和苹果的故事，而是讲述一位有野心、有心计的女性如何登上名利之巅。“彗星美人”体现了演艺圈一代新人换旧人的残酷现实。\\nPlot: An ingenue insinuates herself in to the company of an established but aging stage actress and her circle of theater friends.\\n《飞屋环游记》\\nUp",
"\\n皮克斯的许多动画片名字都非常简洁，除了Up，还有Cars，Wall.E等。如果直接翻译成“向上”、“汽车”、“瓦力”未免太过直白。针对动画片受众年龄比较低的特点，也就诞生了“飞屋环游记”这样更具体形象的译名。\\nPlot: Seventy-eight year old Carl Fredricksen travels to Paradise Falls in his home equipped with balloons, inadvertently taking a young stowaway."],
"photo":["src=\"http://221.181.100.37:8082/upload-image/images/20180829/20180829152809971.jpg\",width=\"640\",height=\"977\"",
"src=\"http://221.181.100.37:8082/upload-image/images/20180829/20180829152819829.jpg\",width=\"640\",height=\"948\"",
"src=\"http://221.181.100.37:8082/upload-image/images/20180829/20180829152828514.jpg\",width=\"640\",height=\"836\"",
"src=\"http://221.181.100.37:8082/upload-image/images/20180829/20180829152836869.jpg\",width=\"640\",height=\"960\"",
"src=\"http://221.181.100.37:8082/upload-image/images/20180829/20180829152845485.jpg\",width=\"640\",height=\"802\"",
"src=\"http://221.181.100.37:8082/upload-image/images/20180829/20180829152852586.jpg\",width=\"466\",height=\"252\"",
"src=\"http://221.181.100.37:8082/upload-image/images/20180829/20180829152901160.jpg\",width=\"640\",height=\"809\"",
"src=\"http://221.181.100.37:8082/upload-image/images/20180829/20180829152908380.jpg\",width=\"640\",height=\"497\""]}                                                                                                                                                   