package TestTools.TvconfTool;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


class Chooseconffile implements ActionListener {
	static String filepath;
	@Override
	public void actionPerformed(ActionEvent e){
		JFileChooser fcDlg = new JFileChooser();
		fcDlg.setDialogTitle("修改tvconf 工具");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("选择文件(*.json)", "json");
		fcDlg.setFileFilter(filter);
		int returnVal = fcDlg.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION){
			filepath = fcDlg.getSelectedFile().getPath();
			SimplePanel.textField0.setText(filepath);
		}
	}
}


class Change implements ActionListener{
	@Override
	//替换文件内容
	public void actionPerformed(ActionEvent e){
		String fp = Chooseconffile.filepath;	
		OpFile tmf = new OpFile(fp);
		try {
			tmf.ReadFile(fp);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			System.out.println(SimplePanel.textField1.getText());
			System.out.println(SimplePanel.textField2.getText());
			tmf.ReplaceValue(fp, SimplePanel.textField1.getText(), SimplePanel.textField2.getText());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}


class Sign implements ActionListener{
	@Override
	//计算MD5
	public void actionPerformed(ActionEvent e) {
		String fp = Chooseconffile.filepath;
		OpFile tmf = new OpFile(fp);
		HashMap<String, String> cs = null;
		try {
			cs = tmf.getConfAndSign(fp);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String conf = cs.keySet().iterator().next();
//		System.out.println(conf);
		String newsign = tmf.getMD532Small(conf);
		System.out.println(newsign);
		try {
			tmf.ReplaceValue(fp,cs.get(conf),newsign);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	
			
	}
}



public class ManyActionEvents {
	public static void main(String[] args) {
	}
}
