using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace testAutoInsuranceWS
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();

            //ws.Url = "http://121.196.222.85:9081/autoInsurance?wsdl";
        }

        testAutoInsuranceWS.localhost.CommandCenterService ws = new localhost.CommandCenterService();

        private void button1_Click(object sender, EventArgs e)
        {
            try
            {
                string rtn = ws.login("{\"insuranceId\": 2, \"ukey\":\"JJ115192BJ\",\"loginName\":\"LGXY-00001\",\"password\":\"RKrv5D61\"}");
                MessageBox.Show(rtn);
            }
            catch (Exception error)
            {
                MessageBox.Show(error.Message);
            }

        }

        private void button2_Click(object sender, EventArgs e)
        {
            try
            {
                textBox4.Text = "";
                string rtn = ws.queryBaseData("{\"insuranceId\": 1, \"chepNu\":\"" + textBox1.Text + "\", \"chejh\":\"" + textBox7.Text + "\", \"fadjh\":\"" + textBox8.Text + "\"}");
                Console.WriteLine(rtn);
                textBox4.Text = rtn;
            }
            catch (Exception error)
            {
                MessageBox.Show(error.Message);
            }
        }

        class SuanfRtnData
        {
            public string saveHebId { set; get; }
            public bool success { set; get; }
            public string message { set; get; }
        }

        private void button6_Click(object sender, EventArgs e)
        {
            int jiaoqOrShangy = -1;

            if (radioButton1.Checked)
                jiaoqOrShangy = 1;
            if (radioButton2.Checked)
                jiaoqOrShangy = 0;
            if (radioButton3.Checked)
                jiaoqOrShangy = 2;

            try
            {
                string _in = "{\"insuranceId\": 2, \"carBaseInfo\":[" + textBox4.Text + "], \"jiaoqOrShangy\":" + jiaoqOrShangy + ", \"jiaoqStartDate\":\"" + dateTimePicker1.Text + "\",\"xianZDetail\":[{\"xianZ\":\"A\",\"bujmP\":true,\"checked\":true},{\"xianZ\":\"B\",\"baoE\":1000000,\"bujmP\":true,\"checked\":true},{\"xianZ\":\"D11\",\"baoE\":10000,\"bujmP\":true,\"checked\":true},{\"xianZ\":\"D12\",\"baoE\":10000,\"bujmP\":true,\"checked\":true},{\"xianZ\":\"G\",\"bujmP\":true,\"checked\":true},{\"xianZ\":\"F\",\"baoE\":10,\"checked\":true},{\"xianZ\":\"X1\",\"bujmP\":true,\"checked\":true},{\"xianZ\":\"Z\",\"bujmP\":true,\"checked\":true},{\"xianZ\":\"L\",\"baoE\":5000,\"bujmP\":true,\"checked\":true}]}";
                //string _in = "{\"insuranceId\": 1, \"carBaseInfo\":[" + textBox4.Text + "],\"jiaoqOrShangy\":" + jiaoqOrShangy + ", \"jiaoqStartDate\":\"" + dateTimePicker1.Text + "\",\"xianZDetail\":[{\"xianZ\":\"A\",\"bujmP\":true,\"checked\":true},{\"xianZ\":\"B\",\"baoE\":500000,\"bujmP\":true,\"checked\":true},{\"xianZ\":\"G\",\"bujmP\":true,\"checked\":true}]}";
                Console.WriteLine(_in);
                string rtn = ws.suanF(_in);
                SuanfRtnData srd = JsonConvert.DeserializeObject <SuanfRtnData>(rtn);
                textBox3.Text = srd.saveHebId;

                Console.WriteLine(rtn);
                MessageBox.Show(rtn);
            }
            catch (Exception error)
            {
                MessageBox.Show(error.Message);
            }
        }

        private void button3_Click(object sender, EventArgs e)
        {
            int jiaoqOrShangy = -1;

            if (radioButton1.Checked)
                jiaoqOrShangy = 1;
            if (radioButton2.Checked)
                jiaoqOrShangy = 0;
            if (radioButton3.Checked)
                jiaoqOrShangy = 2;

            try
            {
                //chezInfo = "{\"customMobile\":\"\",\"addressCName\":\"北京市朝阳区安慧北里逸园小区26号楼602号\",\"customerCode\":\"1100100000160886\",\"identifyNumber\":\"430121198002032019\",\"customerCName\":\"何超\"}";
                //string rtn = ws.saveAndHeB("{\"insuranceId\": 1, \"hebXianZ\": \"" + jiaoqOrShangy + "\", \"saveHebId\": \"" + textBox3.Text + "\", relation: [{\"type\":\"cheZ\",\"customMobile\":\"13501235344\",\"addressCName\":\"北京市朝阳区安慧北里逸园小区26号楼602号\",\"customerCode\":\"1100100000160886\",\"identifyNumber\":\"430121198002032019\",\"customerCName\":\"何超\"},{\"type\":\"touBR\",\"customMobile\":\"13501235344\",\"addressCName\":\"北京市朝阳区安慧北里逸园小区26号楼602号\",\"customerCode\":\"1100100000160886\",\"identifyNumber\":\"430121198002032019\",\"customerCName\":\"何超\"},{\"type\":\"beiBXR\",\"customMobile\":\"13501235344\",\"addressCName\":\"北京市朝阳区安慧北里逸园小区26号楼602号\",\"customerCode\":\"1100100000160886\",\"identifyNumber\":\"430121198002032019\",\"customerCName\":\"何超\"}]}");
                string rtn = ws.saveAndHeB("{\"insuranceId\": 1, \"hebXianZ\": \"" + jiaoqOrShangy + "\", \"saveHebId\": \"" + textBox3.Text + "\", relation: [" + chezInfo + "]}");
                MessageBox.Show(rtn);
            }
            catch (Exception error)
            {
                MessageBox.Show(error.Message);
            }
        }

        private void button4_Click(object sender, EventArgs e)
        {
            try
            {
                string rtn = ws.queryBaodhByToubdh("{\"insuranceId\": 2, \"toubdh\": \"" + textBox2.Text + "\"}");
                Console.WriteLine(rtn);
                MessageBox.Show(rtn);
            }
            catch (Exception error)
            {
                MessageBox.Show(error.Message);
            }
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            label1.Text = ws.Url;
        }

        private void button5_Click(object sender, EventArgs e)
        {
            try
            {
                string rtn = ws.queryBaoxgsList();
                Console.WriteLine(rtn);
                MessageBox.Show(rtn);
            }
            catch (Exception error)
            {
                MessageBox.Show(error.Message);
            }
        }

        string chezInfo = "";
        private void button7_Click(object sender, EventArgs e)
        {
            try
            {
                string rtn = ws.queryRelationData("{\"insuranceId\": 2, \"shenfzNu\": \"" + textBox5.Text + "\"}");
                chezInfo = rtn;
                Console.WriteLine(rtn);
                MessageBox.Show(rtn);
            }
            catch (Exception error)
            {
                MessageBox.Show(error.Message);
            }
        }

        private void button8_Click(object sender, EventArgs e)
        {
            try
            {
                string rtn = ws.queryHebJg("{\"insuranceId\": 1, \"toubDh\": \"" + textBox6.Text + "\"}");
                chezInfo = rtn;
                Console.WriteLine(rtn);
                MessageBox.Show(rtn);
            }
            catch (Exception error)
            {
                MessageBox.Show(error.Message);
            }
        }
       
    }
}
