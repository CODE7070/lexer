package lexer1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import com.sun.org.apache.bcel.internal.generic.NEW;
/**
 * �ʷ���������ֱ�ӱ�д���������Զ���
 *
 */
public class Lexer {
	//token
	public static final int NUM=256;
	public static final int ID=257;
	public static final int TRUE=258;
	public static final int FALSE=259;
	//ÿ�ζ�����ַ�
	private char c=' ';
	//���������еĹؼ���
	private Hashtable words=new Hashtable();
	
	public Lexer(){
		words.put("true", new Word(TRUE, "true"));
		words.put("false", new Word(FALSE, "false"));
	}
	//��������
	public Token scan() throws IOException{
		String word="";
		
		for(;;c=(char)System.in.read()){
			if(c==' '||c=='\t'||c=='\r'||c=='\n') continue;
			else break;
		}
		if(Character.isDigit(c)){
			do{
				word+=c;
				c=(char)System.in.read();
			}while(Character.isDigit(c));
			Num num=new Num(NUM, Integer.parseInt(word));
			return num;
		}else if(Character.isAlphabetic(c)){
			do{
				word+=c;
				c=(char)System.in.read();
			}while(Character.isAlphabetic(c));
			Word word3=(Word) words.get(word);
			if(word3!=null) return word3;
			Word word2=new Word( ID,word);
			return word2;
		}
		
		Token t=new Token(0);
		return t;
		
		
		
	}
	public static void main(String[] args) throws IOException {
		Lexer lexer=new Lexer();
		while(true){
			Token t=lexer.scan();
			switch (t.getId()) {
			case Lexer.NUM:
				System.out.println("ʶ������:"+((Num) t).getValue());
				break;
			case Lexer.ID:
				System.out.println("ʶ���ַ���"+((Word) t).elem);
				break;
			case Lexer.TRUE:
				System.out.println("ʶ�𵽲���ֵ��"+((Word) t).elem);
				break;
			case Lexer.FALSE:
				System.out.println("ʶ�𵽲���ֵ:"+((Word) t).elem);
				break;

			default:
				System.out.println("�޷�ʶ��");
				break;
			}
			
		}
		
	}

}
