<template>
  <div class="chat-container cyber-bg">
    <!-- Header -->
    <header class="chat-header">
      <button class="back-btn" @click="goBack">
        <svg viewBox="0 0 24 24" fill="none" stroke="currentColor">
          <path d="M19 12H5M12 19l-7-7 7-7" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
        </svg>
      </button>
      <div class="header-title">
        <div class="avatar-ai">
          <svg viewBox="0 0 24 24" fill="none">
            <rect x="3" y="3" width="18" height="18" rx="3" fill="#0f0f1a" stroke="#00f5ff" stroke-width="2"/>
            <circle cx="9" cy="10" r="1.5" fill="#00f5ff"/>
            <circle cx="15" cy="10" r="1.5" fill="#00f5ff"/>
            <path d="M8 15h8" stroke="#00f5ff" stroke-width="2" stroke-linecap="round"/>
          </svg>
        </div>
        <div class="title-text">
          <h2>超级智能体</h2>
          <span class="status">在线</span>
        </div>
      </div>
      <div class="agent-tag">MULTI-STEP</div>
    </header>

    <!-- Messages -->
    <div class="chat-messages" ref="messagesRef">
      <div
        v-for="(msg, index) in messages"
        :key="index"
        :class="['message', msg.role === 'user' ? 'user-message' : 'ai-message']"
      >
        <div class="message-avatar">
          <div v-if="msg.role === 'user'" class="avatar user-avatar">
            <svg viewBox="0 0 24 24" fill="none" stroke="currentColor">
              <circle cx="12" cy="7" r="4" stroke-width="2"/>
              <path d="M5 21v-2a7 7 0 017-7h0a7 7 0 017 7v2" stroke-width="2"/>
            </svg>
          </div>
          <div v-else class="avatar ai-avatar">
            <svg viewBox="0 0 24 24" fill="none">
              <rect x="3" y="3" width="18" height="18" rx="3" fill="#0f0f1a" stroke="#00f5ff" stroke-width="2"/>
              <circle cx="9" cy="10" r="1.5" fill="#00f5ff"/>
              <circle cx="15" cy="10" r="1.5" fill="#00f5ff"/>
              <path d="M8 15h8" stroke="#00f5ff" stroke-width="2" stroke-linecap="round"/>
            </svg>
          </div>
        </div>
        <div class="message-content">{{ msg.content }}</div>
      </div>
      <div v-if="isTyping" class="message ai-message">
        <div class="message-avatar">
          <div class="avatar ai-avatar">
            <svg viewBox="0 0 24 24" fill="none">
              <rect x="3" y="3" width="18" height="18" rx="3" fill="#0f0f1a" stroke="#00f5ff" stroke-width="2"/>
              <circle cx="9" cy="10" r="1.5" fill="#00f5ff"/>
              <circle cx="15" cy="10" r="1.5" fill="#00f5ff"/>
              <path d="M8 15h8" stroke="#00f5ff" stroke-width="2" stroke-linecap="round"/>
            </svg>
          </div>
        </div>
        <div class="message-content typing">
          <span></span><span></span><span></span>
        </div>
      </div>
    </div>

    <!-- Input -->
    <div class="chat-input">
      <input
        v-model="inputMessage"
        @keyup.enter="sendMessage"
        placeholder="输入任务或问题..."
        :disabled="isTyping"
        class="input-field"
      />
      <button 
        @click="sendMessage" 
        :disabled="isTyping || !inputMessage.trim()"
        class="send-btn"
      >
        <svg viewBox="0 0 24 24" fill="none" stroke="currentColor">
          <path d="M22 2L11 13M22 2l-7 20-4-9-9-4 20-7z" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
        </svg>
      </button>
    </div>
  </div>
</template>

<script setup>
import { ref, nextTick } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const messagesRef = ref(null)
const messages = ref([])
const inputMessage = ref('')
const isTyping = ref(false)
let eventSource = null

const goBack = () => {
  if (eventSource) {
    eventSource.close()
  }
  router.push('/')
}

const scrollToBottom = () => {
  nextTick(() => {
    if (messagesRef.value) {
      messagesRef.value.scrollTop = messagesRef.value.scrollHeight
    }
  })
}

const sendMessage = async () => {
  if (!inputMessage.value.trim() || isTyping.value) return

  const userMsg = inputMessage.value.trim()
  messages.value.push({ role: 'user', content: userMsg })
  inputMessage.value = ''
  scrollToBottom()

  isTyping.value = true

  try {
    const url = `/api/ai/manus/chat?message=${encodeURIComponent(userMsg)}`
    eventSource = new EventSource(url)

    eventSource.onmessage = (event) => {
      if (event.data) {
        messages.value.push({ role: 'ai', content: event.data })
        scrollToBottom()
      }
    }

    eventSource.onerror = () => {
      eventSource.close()
      isTyping.value = false
    }

    eventSource.addEventListener('close', () => {
      eventSource.close()
      isTyping.value = false
    })
  } catch (error) {
    console.error('SSE连接失败:', error)
    messages.value.push({ role: 'ai', content: '抱歉，服务暂时不可用，请稍后再试。' })
    isTyping.value = false
  }
}
</script>

<style scoped>
.chat-container {
  display: flex;
  flex-direction: column;
  height: 100vh;
  background: var(--bg-primary);
  position: relative;
}

/* Header */
.chat-header {
  background: linear-gradient(135deg, var(--bg-secondary) 0%, var(--bg-tertiary) 100%);
  padding: 16px 24px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  border-bottom: 1px solid rgba(0, 245, 255, 0.2);
  box-shadow: 0 2px 20px rgba(0, 0, 0, 0.3);
  z-index: 100;
}

.back-btn {
  width: 44px;
  height: 44px;
  border-radius: 12px;
  background: rgba(255, 255, 255, 0.05);
  border: 1px solid rgba(0, 245, 255, 0.2);
  color: var(--text-primary);
  cursor: pointer;
  transition: all 0.3s;
  display: flex;
  align-items: center;
  justify-content: center;
}

.back-btn:hover {
  background: rgba(0, 245, 255, 0.1);
  border-color: var(--accent-cyan);
  box-shadow: var(--glow-cyan);
}

.back-btn svg {
  width: 24px;
  height: 24px;
}

.header-title {
  display: flex;
  align-items: center;
  gap: 14px;
}

.avatar-ai {
  width: 48px;
  height: 48px;
  border-radius: 12px;
  background: linear-gradient(135deg, #0f0f1a, #151525);
  display: flex;
  align-items: center;
  justify-content: center;
  box-shadow: var(--glow-cyan);
  border: 1px solid rgba(0, 245, 255, 0.3);
}

.avatar-ai svg {
  width: 28px;
  height: 28px;
}

.title-text {
  display: flex;
  flex-direction: column;
  text-align: left;
}

.title-text h2 {
  font-size: 1.1rem;
  font-weight: 700;
  color: var(--text-primary);
  margin: 0;
}

.status {
  font-size: 0.75rem;
  color: var(--accent-green);
  display: flex;
  align-items: center;
  gap: 5px;
}

.status::before {
  content: '';
  width: 8px;
  height: 8px;
  background: var(--accent-green);
  border-radius: 50%;
  box-shadow: 0 0 10px var(--accent-green);
  animation: pulse-glow 1.5s ease-in-out infinite;
}

.agent-tag {
  font-family: 'Courier New', monospace;
  font-size: 0.7rem;
  color: var(--accent-cyan);
  background: rgba(0, 245, 255, 0.1);
  padding: 6px 12px;
  border-radius: 6px;
  border: 1px solid rgba(0, 245, 255, 0.3);
  letter-spacing: 0.1em;
}

/* Messages */
.chat-messages {
  flex: 1;
  overflow-y: auto;
  padding: 24px;
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.message {
  display: flex;
  gap: 12px;
  max-width: 80%;
}

.user-message {
  align-self: flex-end;
  flex-direction: row-reverse;
}

.ai-message {
  align-self: flex-start;
}

.message-avatar {
  flex-shrink: 0;
}

.avatar {
  width: 40px;
  height: 40px;
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.user-avatar {
  background: linear-gradient(135deg, var(--accent-purple), #a78bfa);
  color: white;
  border-radius: 50%;
}

.ai-avatar {
  background: linear-gradient(135deg, #0f0f1a, #151525);
  border: 1px solid rgba(0, 245, 255, 0.3);
}

.avatar svg {
  width: 22px;
  height: 22px;
}

.message-content {
  background: var(--bg-secondary);
  padding: 14px 18px;
  border-radius: 16px;
  border: 1px solid rgba(255, 255, 255, 0.05);
  line-height: 1.6;
  text-align: left;
  color: var(--text-primary);
  word-break: break-word;
}

.user-message .message-content {
  background: linear-gradient(135deg, rgba(139, 92, 246, 0.2));
  border: 1px solid rgba(139, 92, 246, 0.3);
}

/* Typing Animation */
.typing {
  display: flex;
  gap: 6px;
  padding: 20px 18px;
  background: var(--bg-secondary);
}

.typing span {
  width: 8px;
  height: 8px;
  background: var(--accent-cyan);
  border-radius: 50%;
  animation: typing 1.4s infinite both;
}

.typing span:nth-child(2) {
  animation-delay: 0.2s;
}

.typing span:nth-child(3) {
  animation-delay: 0.4s;
}

@keyframes typing {
  0%, 80%, 100% {
    transform: scale(0);
    opacity: 0.5;
  }
  40% {
    transform: scale(1);
    opacity: 1;
  }
}

/* Input */
.chat-input {
  padding: 16px 24px;
  background: var(--bg-secondary);
  border-top: 1px solid rgba(0, 245, 255, 0.2);
  display: flex;
  gap: 12px;
  box-shadow: 0 -2px 20px rgba(0, 0, 0, 0.3);
}

.input-field {
  flex: 1;
  padding: 14px 20px;
  background: var(--bg-tertiary);
  border: 2px solid rgba(0, 245, 255, 0.2);
  border-radius: 12px;
  font-size: 1rem;
  color: var(--text-primary);
  outline: none;
  transition: all 0.3s;
}

.input-field::placeholder {
  color: var(--text-secondary);
}

.input-field:focus {
  border-color: var(--accent-cyan);
  box-shadow: var(--glow-cyan);
}

.send-btn {
  width: 50px;
  height: 50px;
  border-radius: 12px;
  background: linear-gradient(135deg, var(--accent-cyan), #00d4ff);
  border: none;
  color: #0f0f1a;
  cursor: pointer;
  transition: all 0.3s;
  display: flex;
  align-items: center;
  justify-content: center;
}

.send-btn:hover:not(:disabled) {
  transform: scale(1.05);
  box-shadow: var(--glow-cyan);
}

.send-btn:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}

.send-btn svg {
  width: 24px;
  height: 24px;
}

/* Responsive */
@media (max-width: 768px) {
  .chat-header {
    padding: 12px 16px;
  }

  .agent-tag {
    display: none;
  }

  .chat-messages {
    padding: 16px;
    gap: 16px;
  }

  .message {
    max-width: 90%;
  }

  .chat-input {
    padding: 12px 16px;
  }
}

@media (max-width: 480px) {
  .title-text h2 {
    font-size: 1rem;
  }

  .avatar {
    width: 36px;
    height: 36px;
  }
}
</style>
