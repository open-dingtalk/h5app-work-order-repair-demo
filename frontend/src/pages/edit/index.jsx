import React, { useState, useEffect } from 'react'
import { Form, Input, Button, NavBar, Toast, Swiper, Dialog } from 'antd-mobile'
import { DeleteOutline } from 'antd-mobile-icons'
import './index.css'
import { useNavigate } from 'react-router-dom'
import short from 'short-uuid'
import img1 from '../../images/1.png'
import img2 from '../../images/2.png'
import img3 from '../../images/3.png'
import img4 from '../../images/4.png'
import img5 from '../../images/5.png'

const Main = () => {
  const navigate = useNavigate()
  const [form] = Form.useForm()
  const [robotForm] = Form.useForm()
  const [loading, setLoading] = useState(false)
  const [items, setItems] = useState([])

  const resetForm = () => {
    try {
      let values = {}
      let saveForm = localStorage.getItem('form')
      saveForm = JSON.parse(saveForm)
      if (saveForm && saveForm.items && saveForm.items.length) {
        setItems(saveForm.items)
        saveForm.items.forEach(item => {
          values[item.id] = item.label
        })
      } else {
        const defaultItems = [
          {id: '001', label: '工单编号'},
          {id: '002', label: '维修工程师姓名'},
          {id: '003', label: '产品型号'},
          {id: '004', label: '故障现象'},
          {id: '005', label: '备注'},
        ]
        setItems(defaultItems)
        defaultItems.forEach(item => {
          values[item.id] = item.label
        })
      }
      return {
        link: saveForm ? saveForm.link : '',
        values
      }
    } catch (err) {
      console.log('err', err)
    }
  }

  const back = () => {
    navigate('/', { replace: true })
  }
  const request = (link, values) => {
    setLoading(true)
    try {
      const items = []
      for(let key in values) {
        if (values[key]) {
          items.push({
            id: key,
            label: values[key]
          })
        }
      }
      if (!items.length) {
        Dialog.alert({
          content: '至少需要配置一个字段',
          onConfirm: () => {}
        })
        setLoading(false)
        return 
      }
      localStorage.setItem('form', JSON.stringify({
        link,
        items
      }))
      Toast.show({
        content: '保存成功',
      })
    } catch (err) {
      console.log('save error:', err)
    }
    setLoading(false)
  }
  const onSubmit = async () => {
    try {
      const robotValues = await robotForm.validateFields()
      const items = await form.validateFields()
      const link = robotValues.link
      request(link, items)
    } catch (errorList) {
      console.log(errorList)
    }
  }
  const addItem = () => {
    setItems(items.concat({
      id: short.generate()
    }))
  }
  const delItems = (index) => {
    items.splice(index, 1)
    setItems([...items])
  }
  useEffect(() => {
    const data = resetForm()
    robotForm.setFieldsValue({
      link: data.link || ''
    })
    form.setFieldsValue(data.values)
  }, [robotForm, form])

  const FormItems = (props) => {
    const formItems = props.items
    return formItems.map((item, index) =>
      <div key={item.id}>
        <Form.Item name={item.id} label={`字段名称`} rules={[]}>
          <Input placeholder={`请输入字段名称`} />
        </Form.Item>
        <DeleteOutline className='del-btn' onClick={() => props.delItems(index)} />
      </div>
    );
  }

  const swiperItems = [img1, img2, img3, img4, img5].map((img, index) => (
    <Swiper.Item key={index}>
      <div className='swiper-item-box'>
        <div className="mask"></div>
        <img src={img} alt="" />
      </div>
    </Swiper.Item>
  ))

  return (
    <div className='page page-edit'>
      <div className='navbar-box'>
        <NavBar onBack={back}>配置工单</NavBar>
      </div>
      <div className="page-content">
        <section>
          <h4>第一步：配置群机器人</h4>
          <Swiper loop>{swiperItems}</Swiper>
        </section>
        <section>
          <h4>第二步：配置群机器人链接</h4>
          <Form className="form" form={robotForm}>
            <Form.Item name="link" label={`机器人链接`} rules={[{ required: true, message: `此项必填` }]}>
              <Input placeholder={`请输入机器人链接`} />
            </Form.Item>
          </Form>
        </section>
        <section>
          <h4>第三步：工单组件，详细工单diy请开通”番茄表单“体验</h4>
          <Form className="form" form={form}>
          {!!items.length && <FormItems items={items} delItems={delItems}></FormItems>}
            <div className='add-item'>
              <Button block onClick={addItem}>
                添加字段
              </Button>
            </div>
          </Form>
        </section>
      </div>
      <div className="submit-box">
        <Button block type="submit" loading={loading} color="primary" onClick={onSubmit}>
          保存
        </Button>
      </div>
    </div>
  )
}
export default Main
