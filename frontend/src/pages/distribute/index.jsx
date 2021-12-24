import React, { useState, useContext, useEffect } from 'react'
import { Form, Input, Button, NavBar, Dialog } from 'antd-mobile'
import './index.css'
import { useNavigate } from 'react-router-dom'
import { UserInfoContext } from '../../App'
import axios from 'axios'
import banner from '../../images/banner.png'

const Main = () => {
  const context = useContext(UserInfoContext)
  const navigate = useNavigate()
  const [form] = Form.useForm()
  const [loading, setLoading] = useState(false)
  const [items, setItems] = useState([])
  const [link, setLink] = useState('')

  const resetForm = () => {
    try {
      let values = {}
      let saveForm = localStorage.getItem('form')
      saveForm = JSON.parse(saveForm)
      if (!saveForm || !saveForm.items || !saveForm.items.length) {
        Dialog.alert({
          content: '需要先配置工单',
          confirmText: '前往配置',
          onConfirm: () => {
            navigate('/edit')
          }
        })
        return
      }
      if (saveForm && saveForm.items && saveForm.items.length) {
        setItems(saveForm.items)
        saveForm.items.forEach(item => {
          values[item.id] = item.label
        })
      }
      setLink(saveForm ? saveForm.link : '')
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
  const request = (values) => {
    setLoading(true)
    const form = {
      link,
      items: items.map(item => {
        item.value = values[item.id]
        return item
      }),
    }
    axios
      .post(context.domain + '/groupRobot/form/submit', form)
      .then((res) => {
        setLoading(false)
        if (res && res.data.success) {
          Dialog.alert({
            content: '提交成功!',
            onConfirm: () => {
              back()
            }
          })
        } else {
          alert('request failed --->' + JSON.stringify(res))
        }
      })
      .catch((error) => {
        setLoading(false)
        alert('httpRequest failed --->' + JSON.stringify(error))
      })
  }
  const onSubmit = async () => {
    try {
      const values = await form.validateFields()
      console.log(values)
      request(values)
    } catch (errorList) {
      console.log(errorList)
    }
  }
  const FormItems = (props) => {
    const formItems = props.items
    return formItems.map((item) =>
      <Form.Item key={item.id} name={item.id} label={item.label} rules={[]}>
        <Input placeholder={`请输入${item.label}`} />
      </Form.Item>
    );
  }

  useEffect(() => {
    resetForm()
    form.setFieldsValue({})
  }, [])

  return (
    <div className='page'>
      <div className='navbar-box'>
        <NavBar onBack={back}>工单派发</NavBar>
      </div>
      <div className="page-content">
        <Form className="form" form={form}>
          <div className="form-banner">
            <img src={banner} alt="" />
          </div>
          <FormItems items={items}></FormItems>
        </Form>
      </div>
      <div className="submit-box">
        <Button block type="submit" loading={loading} color="primary" onClick={onSubmit}>
          提交
        </Button>
      </div>
    </div>
  )
}
export default Main
