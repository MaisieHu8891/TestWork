package TestTools.ZlibTool;

public class DoDecomp {
	
	public static void main(String[] args) throws Exception{
		String HexStr = "789c54904d4eeb301cc4dfbbcaac8d643b5f8d775c00a927406ee2168bc68e12975255d982a22e106b361ca1cb4a5ca745700bf40f2d946c228d677e9ef1bf3fdfffd735c2aa365088840043b0958112891032e15922184a1d34d41ad3c657f4b7a571c186154538189c2d6eaf34a5f0f9f6fcf1b2195f8ec1d0d8921c591ecb84c76068ebebb32847c7103c01cf00fb5d7fd83ebdf79bc3c3f6f0d8ef77fd0f2997519c27694a82f7d5a00919f3588ea8b627f1c42dbc0bc605824fccccbaef51f85d05f22c5c28fdd20d0734c4dc513be2567a3ebf98b44b12ef43a3a1008659e3173514122da2329ff242cabcd0b2c838975136a51af55c87f6c62f87220cb56f6db07eb8824916b1f8d8fee8393d780b85519a8161d19aa6f0d5c443218dd075dd17000000ffff";
		String HexS = DataFormat.str2HexStr(HexStr);
		System.out.println(HexS);
		if (DataFormat.checkHexStr(HexS)){
			System.out.println("true");
			byte[] datacomp = DataFormat.hexStr2Bytes(HexS);
			ZlibDecompress zd = new ZlibDecompress();
			byte[] out = zd.decompress(datacomp);
			String outstr = DataFormat.byte2HexStr(out, out.length);
			String aa = DataFormat.hexStr2Str(outstr);
			System.out.println(aa);
			
		}
		else{
			System.out.println("fail");
		}
		
		
	}

}
